package com.hyperdesign.delivaryUser.feature.authentication.changepassword.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest

interface IChangePasswordRemoteDataSource {
    suspend fun changePassword(request: ChangePasswordRequest): AuthenticationDto
}