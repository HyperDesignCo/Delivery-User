package com.example.delivaryUser.common.data.repository.remote

import com.example.delivaryUser.common.data.DelivaryUserException
import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.service.language.domain.usecase.GetLanguageUseCase
import com.example.delivaryUser.service.user.domain.interactors.GetTokenUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

@OptIn(DelicateCoroutinesApi::class)
fun provideHttpClient(json: Json, language: GetLanguageUseCase, getToken: GetTokenUseCase) = HttpClient(Android) {
    val lang = runBlocking {
        when (val resource = language.invoke(Unit).first()) {
            is Resource.Success -> resource
            else -> "en"
        }
    }
    val token = runBlocking {
        when (val resource = getToken.invoke(Unit)) {
            is Resource.Success -> resource.model
            else -> ""
        }
    }
    expectSuccess = true
    install(ContentNegotiation) {
        json()
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = Logger.ANDROID
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 30_000
        connectTimeoutMillis = 60_000
        socketTimeoutMillis = 20_000
    }
    defaultRequest {
        url("https://delivery-online.com/api/user/")
        contentType(ContentType.Application.Json)
        header("Accept-Language", lang)
        val token = runBlocking {
            when (val resource = getToken.invoke(Unit)) {
                is Resource.Success -> resource.model
                else -> ""
            }
        }
        if (token.isNotBlank())
            header("Authorization", "Bearer $token")
    }
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, request ->
            if (exception is ResponseException) throw handleResponseException(
                exception.response,
                json
            )
        }
    }

}

private suspend fun handleResponseException(
    response: HttpResponse,
    json: Json,
): DelivaryUserException {
    when (val statusCode = response.status.value) {
        HttpStatusCode.Unauthorized.value -> throw DelivaryUserException.Client.UnAuthorized(message = response.status.description)
        HttpStatusCode.UnprocessableEntity.value -> {
            val responseBodyText = response.bodyAsText()
            return responseValidationMapping(
                json.decodeFromString<APIErrorResponse>(
                    responseBodyText
                )
            )
        }

        HttpStatusCode.NotFound.value -> throw DelivaryUserException.Client.NotFound(message = response.status.description)

        HttpStatusCode.InternalServerError.value -> return DelivaryUserException.Server.InternalServerError(
            httpErrorCode = statusCode, message = response.status.description
        )
        else -> throw DelivaryUserException.Client.Unhandled(
            errorCode = statusCode, message = response.status.description
        )
    }
}

private fun responseValidationMapping(errorResponse: APIErrorResponse): DelivaryUserException {
    return DelivaryUserException.Client.ResponseValidation(
        errors = errorResponse.errors?.mapNotNull { (key, value) ->
            if (key == ErrorKeyEnum.UNKNOWN) null else key to value.first()
        }?.toMap() ?: emptyMap(), message = errorResponse.message
    )
}