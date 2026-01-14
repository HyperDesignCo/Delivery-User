package com.example.delivaryUser.feature.authentication.forgetpassword.data.repository

import com.example.delivaryUser.feature.authentication.forgetpassword.data.mapper.ForgetPasswordMapper
import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.model.domain.ForgetPassword
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.repository.IForgetPasswordRepository
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.repository.remote.IForgetPasswordRemoteDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class ForgetPasswordRepository(
    private val remote: IForgetPasswordRemoteDataSource,
    private val local: IUserLocalDataSource,
) : IForgetPasswordRepository {
    override suspend fun forgetPassword(request: ForgetPasswordRequest): ForgetPassword =
        ForgetPasswordMapper.dtoToDomain(model = remote.forgetPassword(request = request, token = local.getToken()))
}