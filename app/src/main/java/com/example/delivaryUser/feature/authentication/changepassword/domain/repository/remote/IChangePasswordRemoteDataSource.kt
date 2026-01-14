package com.example.delivaryUser.feature.authentication.changepassword.domain.repository.remote

import com.example.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.example.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest

interface IChangePasswordRemoteDataSource {
    suspend fun changePassword(request: ChangePasswordRequest, token: String): AuthenticationDto
}