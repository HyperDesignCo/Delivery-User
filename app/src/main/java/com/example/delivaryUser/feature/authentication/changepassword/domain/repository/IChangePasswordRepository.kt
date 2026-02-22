package com.example.delivaryUser.feature.authentication.changepassword.domain.repository

import com.example.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.example.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest

interface IChangePasswordRepository {
    suspend fun changePassword(request: ChangePasswordRequest) : Authentication
}