package com.example.delivaryUser.common.data.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

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
        return response.body()
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
}