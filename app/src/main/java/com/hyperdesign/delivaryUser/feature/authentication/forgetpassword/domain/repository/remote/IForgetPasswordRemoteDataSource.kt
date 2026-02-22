package com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.model.dto.ForgetPasswordDto
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest

interface IForgetPasswordRemoteDataSource {
    suspend fun forgetPassword(request: ForgetPasswordRequest): ForgetPasswordDto
}