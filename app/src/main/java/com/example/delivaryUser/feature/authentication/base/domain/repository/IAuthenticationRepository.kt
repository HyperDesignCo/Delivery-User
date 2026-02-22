package com.example.delivaryUser.feature.authentication.base.domain.repository

import com.example.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.example.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.example.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest

interface IAuthenticationRepository {
    suspend fun login(request: LoginRequest): Authentication
    suspend fun register(request: RegisterRequest): Authentication
}