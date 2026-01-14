package com.example.delivaryUser.feature.authentication.forgetpassword.domain.repository

import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.model.domain.ForgetPassword

interface IForgetPasswordRepository {
    suspend fun forgetPassword(request: ForgetPasswordRequest): ForgetPassword
}