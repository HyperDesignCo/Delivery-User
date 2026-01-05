package com.example.delivary_user_android.common.domain.remote

import kotlinx.serialization.KSerializer

/**
 * Interface for handling all Remote Requests in a generic matter
 * [ Post method ]First endpoint ( /endpoint)
 * [ Post method ]Second params for request parameters of type map<String, Any> so the string is the key any is the value
 * [ Post method ] Third headers for request headers of type map<String, Any> so the string is the key any is the value
 * [ Post method ]Fourth Request body of generic type because it can handle any type of data
 * [ Post method ] Serializer of type KSerializer so we can serialize response
 * [ Get method ]First endpoint ( /endpoint)
 * [ Get method ]Second params for request parameters of type map<String, Any> so the string is the key any is the value
 * [ Get method ] Third headers for request headers of type map<String, Any> so the string is the key any is the value
 * [ Get method ] Serializer of type KSerializer so we can serialize response
 * [ Get method ] It does not have Request Body because it is a get request
 * [ Put method ]First endpoint ( /endpoint)
 * [ Put method ]Second params for request parameters of type map<String, Any> so the string is the key any is the value
 * [ Put method ] Third headers for request headers of type map<String, Any> so the string is the key any is the value
 * [ Put method ]Fourth Request body of generic type because it can handle any type of data
 * [ Put method ] Serializer of type KSerializer so we can serialize response
 * [ Delete method ]First endpoint ( /endpoint)
 * [ Delete method ]Second params for request parameters of type map<String, Any> so the string is the key any is the value
 * [ Delete method ] Third headers for request headers of type map<String, Any> so the string is the key any is the value
 * [ Delete method ] Serializer of type KSerializer so we can serialize response
 * [ Delete method ] It does not have Request Body because it is a delete request
 *
 *  Created By Rodina Mo'men 2025/01/05
 */
interface IRemoteDataSourceProvider {
    suspend fun <ResponseBody, RequestBody> post(
        endpoint: String,
        params: Map<String, Any>? = null,
        headers: Map<String, Any>? = null,
        requestBody: RequestBody? = null,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody

    suspend fun <ResponseBody> get(
        endpoint: String,
        params: Map<String, Any>? = null,
        headers: Map<String, Any>? = null,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody

    suspend fun <ResponseBody, RequestBody> put(
        endpoint: String,
        params: Map<String, Any>,
        headers: Map<String, Any>? = null,
        requestBody: RequestBody? = null,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody

    suspend fun <ResponseBody> delete(
        endpoint: String,
        params: Map<String, Any>? = null,
        headers: Map<String, Any>? = null,
        serializer: KSerializer<ResponseBody>,
    ): ResponseBody
}