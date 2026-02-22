package com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest

interface IAuthenticationRemoteDataSource {
    suspend fun login(request: LoginRequest): AuthenticationDto
    suspend fun register(request: RegisterRequest): AuthenticationDto
}