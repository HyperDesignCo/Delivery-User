package com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository

import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest

interface IAuthenticationRepository {
    suspend fun login(request: LoginRequest): Authentication
    suspend fun register(request: RegisterRequest): Authentication
}