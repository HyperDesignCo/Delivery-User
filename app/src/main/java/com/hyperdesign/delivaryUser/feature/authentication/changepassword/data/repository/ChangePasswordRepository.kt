package com.hyperdesign.delivaryUser.feature.authentication.changepassword.data.repository

import com.hyperdesign.delivaryUser.feature.authentication.base.data.mapper.AuthenticationMapper
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.domain.repository.IChangePasswordRepository
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.domain.repository.remote.IChangePasswordRemoteDataSource
import com.hyperdesign.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class ChangePasswordRepository(private val remote: IChangePasswordRemoteDataSource, val local: IUserLocalDataSource) :
    IChangePasswordRepository {
    override suspend fun changePassword(request: ChangePasswordRequest): Authentication {
        val user = local.getUser()
        val result = remote.changePassword(request.copy(phone = user.phone))
        local.savePassword(password = request.password)
        return AuthenticationMapper.dtoToDomain(model = result)
    }
}