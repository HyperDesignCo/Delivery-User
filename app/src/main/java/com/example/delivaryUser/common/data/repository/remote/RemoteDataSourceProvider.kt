package com.example.delivaryUser.common.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class RemoteDataSourceProvider(private val ktorApiService: ApiService, private val json: Json) :
    IRemoteDataSourceProvider {
    override suspend fun <ResponseBody, RequestBody> post(
        endpoint: String,
        params: Map<String, Any>?,
        headers: Map<String, Any>?,
        requestBody: RequestBody?,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody {
        val result = ktorApiService.post(
            endPoint = endpoint,
            params = params,
            headers = headers,
            requestBody = requestBody ?: Unit
        )
        return handleResponse(result, serializer)
    }

    override suspend fun <ResponseBody> get(
        endpoint: String,
        params: Map<String, Any>?,
        headers: Map<String, Any>?,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody {
        val result = ktorApiService.get(endPoint = endpoint, params = params, headers = headers)
        return handleResponse(result, serializer)
    }

    override suspend fun <ResponseBody, RequestBody> put(
        endpoint: String,
        params: Map<String, Any>,
        headers: Map<String, Any>?,
        requestBody: RequestBody?,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody {
        val result = ktorApiService.put(
            endPoint = endpoint,
            params = params,
            headers = headers,
            requestBody = requestBody ?: Unit
        )
        return handleResponse(result, serializer)
    }

    override suspend fun <ResponseBody> delete(
        endpoint: String,
        params: Map<String, Any>?,
        headers: Map<String, Any>?,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody {
        val result = ktorApiService.delete(
            endPoint = endpoint, params = params, headers = headers ?: emptyMap()
        )
        return handleResponse(result, serializer)
    }

    private suspend fun <ResponseBody> handleResponse(
        response: HttpResponse, serializer: KSerializer<ResponseBody>,
    ): ResponseBody {
        val responseBodyText = response.bodyAsText()
        return json.decodeFromString(serializer, responseBodyText)
    }
}