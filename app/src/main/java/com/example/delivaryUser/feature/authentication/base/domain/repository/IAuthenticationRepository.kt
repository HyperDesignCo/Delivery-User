package com.example.delivaryUser.feature.authentication.base.domain.repository

import com.example.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.example.delivaryUser.feature.authentication.login.data.models.request.LoginRequest

interface IAuthenticationRepository {
    suspend fun login(request: LoginRequest): Authentication
}