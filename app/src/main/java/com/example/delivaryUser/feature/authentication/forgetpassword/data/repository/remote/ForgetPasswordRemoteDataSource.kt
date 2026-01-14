package com.example.delivaryUser.feature.authentication.forgetpassword.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.dto.ForgetPasswordDto
import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.repository.remote.IForgetPasswordRemoteDataSource

class ForgetPasswordRemoteDataSource(private val provider: IRemoteDataSourceProvider) :
    IForgetPasswordRemoteDataSource {
    override suspend fun forgetPassword(request: ForgetPasswordRequest, token: String): ForgetPasswordDto =
        provider.post(
            endpoint = FORGET_PASSWORD_ENDPOINT,
            headers = mapOf("Authorization" to "Bearer $token"),
            requestBody = request,
            serializer = ForgetPasswordDto.serializer()
        )

    companion object {
        const val FORGET_PASSWORD_ENDPOINT = "User_forget_password"
    }
}