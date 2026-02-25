package com.hyperdesign.delivaryUser.feature.authentication.base.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.remote.IAuthenticationRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.SocialLoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest

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

    override suspend fun socialLogin(request: SocialLoginRequest): AuthenticationDto = remoteProvider.post(
        endpoint = SOCIAL_LOGIN_ENDPOINT,
        requestBody = request,
        serializer = AuthenticationDto.serializer()
    )

    companion object {
        const val LOGIN_ENDPOINT = "login"
        const val REGISTER_ENDPOINT = "register"
        const val SOCIAL_LOGIN_ENDPOINT = "social_login"
    }
}