package com.example.delivaryUser.feature.authentication.forgetpassword.domain.repository.remote

import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.dto.ForgetPasswordDto
import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest

interface IForgetPasswordRemoteDataSource {
    suspend fun forgetPassword(request: ForgetPasswordRequest, token :String): ForgetPasswordDto
}