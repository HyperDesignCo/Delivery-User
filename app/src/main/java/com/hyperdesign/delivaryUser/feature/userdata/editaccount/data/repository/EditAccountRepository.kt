package com.hyperdesign.delivaryUser.feature.userdata.editaccount.data.repository

import com.hyperdesign.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest
import com.hyperdesign.delivaryUser.feature.userdata.editaccount.domain.repository.IEditAccountRepository
import com.hyperdesign.delivaryUser.feature.userdata.editaccount.domain.repository.remote.IEditAccountRemoteDataSource
import com.hyperdesign.delivaryUser.service.user.data.dto.UserDto
import com.hyperdesign.delivaryUser.service.user.data.mapper.UserMapper
import com.hyperdesign.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class EditAccountRepository(private val remote: IEditAccountRemoteDataSource, private val local: IUserLocalDataSource) :
    IEditAccountRepository {
    override suspend fun editAccount(request: EditAccountRequest) {
        val result = remote.editAccount(request)
        local.saveUser(user = UserMapper.dtoToEntity(result.user ?: UserDto()))
    }
}