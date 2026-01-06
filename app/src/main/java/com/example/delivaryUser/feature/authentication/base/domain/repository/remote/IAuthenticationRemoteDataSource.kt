package com.example.delivaryUser.feature.authentication.base.domain.repository.remote

import com.example.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.example.delivaryUser.feature.authentication.login.data.models.request.LoginRequest

interface IAuthenticationRemoteDataSource {
    suspend fun login(request: LoginRequest): AuthenticationDto
}