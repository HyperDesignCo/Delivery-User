package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.repository

import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.mapper.VerifyPhoneMapper
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.request.VerifyPhoneRequest
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository.IVerifyPhoneRepository
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.domain.repository.remote.IVerifyPhoneRemoteDataSource

class VerifyPhoneRepository(
    private val remote: IVerifyPhoneRemoteDataSource,
) : IVerifyPhoneRepository {
    override suspend fun verifyPhone(request: VerifyPhoneRequest): String =
        VerifyPhoneMapper.dtoToDomain(model = remote.verifyPhone(request = request))
}