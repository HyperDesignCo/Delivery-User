package com.example.delivaryUser.feature.authentication.base.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.example.delivaryUser.feature.authentication.base.domain.repository.remote.IAuthenticationRemoteDataSource
import com.example.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.example.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest

class AuthenticationRemoteDataSource(private val remoteProvider: IRemoteDataSourceProvider) :
    IAuthenticationRemoteDataSource {
    override suspend fun login(request: LoginRequest): AuthenticationDto = remoteProvider.post(
        endpoint = LOGIN_ENDPOINT,
        requestBody = request,
        serializer = AuthenticationDto.serializer()
    )

    override suspend fun register(request: RegisterRequest): AuthenticationDto = remoteProvider.post(
        endpoint = REGISTER_ENDPOINT,
        requestBody = request,
        serializer = AuthenticationDto.serializer()
    )

    companion object {
        const val LOGIN_ENDPOINT = "login"
        const val REGISTER_ENDPOINT = "register"
    }
}