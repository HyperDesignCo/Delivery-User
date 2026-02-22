package com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.repository

import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.mapper.ForgetPasswordMapper
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.data.model.request.ForgetPasswordRequest
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.model.domain.ForgetPassword
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.repository.IForgetPasswordRepository
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.domain.repository.remote.IForgetPasswordRemoteDataSource

class ForgetPasswordRepository(
    private val remote: IForgetPasswordRemoteDataSource,
) : IForgetPasswordRepository {
    override suspend fun forgetPassword(request: ForgetPasswordRequest): ForgetPassword =
        ForgetPasswordMapper.dtoToDomain(model = remote.forgetPassword(request = request))
}