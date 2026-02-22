package com.example.delivaryUser.common.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IFile
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.InternalAPI

class ApiService(private val client: HttpClient) {
    suspend fun get(
        endPoint: String,
        params: Map<String, Any>? = emptyMap(),
        headers: Map<String, Any>? = emptyMap(),
    ): HttpResponse {
        val response: HttpResponse = client.get(endPoint) {
            params?.forEach { (key, value) ->
                url.parameters.append(key, value.toString())
            }
            headers?.forEach { (key, value) ->
                header(key, value)
            }
        }
        return response
    }

    suspend fun post(
        endPoint: String,
        params: Map<String, Any>? = emptyMap(),
        headers: Map<String, Any>? = emptyMap(),
        requestBody: Any,
    ): HttpResponse {
        val response: HttpResponse = client.post(endPoint) {
            params?.forEach { (key, value) ->
                url.parameters.append(key, value.toString())
            }
            headers?.forEach { (key, value) ->
                header(key, value)
            }
            setBody(requestBody)
        }
        return response
    }

    suspend fun delete(
        endPoint: String,
        params: Map<String, Any>? = emptyMap(),
        headers: Map<String, Any> = emptyMap(),
    ): HttpResponse {
        val response: HttpResponse = client.delete(endPoint) {
            params?.forEach { (key, value) ->
                url.parameters.append(key, value.toString())
            }
            headers.forEach { (key, value) ->
                header(key, value)
            }
        }
        return response
    }

    suspend fun put(
        endPoint: String,
        params: Map<String, Any>? = emptyMap(),
        headers: Map<String, Any>? = emptyMap(),
        requestBody: Any,
    ): HttpResponse {
        val response: HttpResponse = client.put(endPoint) {
            params?.forEach { (key, value) ->
                url.parameters.append(key, value.toString())
            }
            headers?.forEach { (key, value) ->
                header(key, value)
            }
            setBody(requestBody)
        }
        return response
    }

    @OptIn(InternalAPI::class)
    suspend fun postWithFile(
        endPoint: String,
        params: Map<String, Any>? = emptyMap(),
        headers: Map<String, Any>? = emptyMap(),
        files: List<Pair<String, IFile>> = emptyList(),
        requestBody: Map<String, Any>,
    ): HttpResponse {
        val response: HttpResponse = client.post(endPoint) {
            params?.forEach { (key, value) ->
                url.parameters.append(key, value.toString())
            }
            headers?.forEach { (key, value) ->
                header(key, value)
            }
            body = MultiPartFormDataContent(
                formData {
                    files.forEach { (key, file) ->
                        append(FormPart(key = key, value = file.value, headers = Headers.build {
                            append(
                                HttpHeaders.ContentDisposition,
                                "form-data; name=\"$key\"; filename=\"${file.name}\""
                            )
                        }))
                    }
                    requestBody.forEach { (key, value) ->
                        append(key = key, value = value)
                    }
                }
            )
        }
        return response
    }

}