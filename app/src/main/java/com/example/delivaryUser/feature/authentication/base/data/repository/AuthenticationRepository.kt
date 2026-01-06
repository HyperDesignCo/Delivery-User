package com.example.delivaryUser.feature.authentication.base.data.repository

import com.example.delivaryUser.feature.authentication.base.data.mapper.AuthenticationMapper
import com.example.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.example.delivaryUser.feature.authentication.base.domain.repository.IAuthenticationRepository
import com.example.delivaryUser.feature.authentication.base.domain.repository.remote.IAuthenticationRemoteDataSource
import com.example.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.example.delivaryUser.service.user.data.dto.UserDto
import com.example.delivaryUser.service.user.data.mapper.UserMapper
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class AuthenticationRepository(val remote: IAuthenticationRemoteDataSource, val local: IUserLocalDataSource) :
    IAuthenticationRepository {
    override suspend fun login(request: LoginRequest): Authentication {
        val result = remote.login(request)
        local.saveUser(user = UserMapper.dtoToEntity(model = result.user ?: UserDto()))
        return AuthenticationMapper.dtoToDomain(model = result)
    }
}