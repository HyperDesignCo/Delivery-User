package com.hyperdesign.delivaryUser.feature.authentication.changepassword.domain.repository

import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest

interface IChangePasswordRepository {
    suspend fun changePassword(request: ChangePasswordRequest) : Authentication
}