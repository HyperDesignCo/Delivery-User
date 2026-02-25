package com.hyperdesign.delivaryUser.feature.authentication.base.data.repository

import com.hyperdesign.delivaryUser.feature.authentication.base.data.mapper.AuthenticationMapper
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.IAuthenticationRepository
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.repository.remote.IAuthenticationRemoteDataSource
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.SocialLoginRequest
import com.hyperdesign.delivaryUser.feature.authentication.register.data.models.request.RegisterRequest
import com.hyperdesign.delivaryUser.service.user.data.dto.UserDto
import com.hyperdesign.delivaryUser.service.user.data.mapper.UserMapper
import com.hyperdesign.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class AuthenticationRepository(val remote: IAuthenticationRemoteDataSource, val local: IUserLocalDataSource) :
    IAuthenticationRepository {
    override suspend fun login(request: LoginRequest): Authentication {
        val result = remote.login(request)
        local.saveUser(user = UserMapper.dtoToEntity(model = result.user ?: UserDto()))
        local.saveToken(token = result.accessToken.orEmpty())
        local.saveRememberMe(request.rememberMe)
        local.saveIsAuthenticated(true)
        local.savePassword(request.password)
        return AuthenticationMapper.dtoToDomain(model = result)
    }

    override suspend fun register(request: RegisterRequest): Authentication {
        val result = remote.register(request)
        local.saveUser(user = UserMapper.dtoToEntity(model = result.user ?: UserDto()))
        local.saveToken(token = result.accessToken.orEmpty())
        local.saveIsAuthenticated(true)
        local.savePassword(request.password)
        return AuthenticationMapper.dtoToDomain(model = result)
    }

    override suspend fun socialLogin(request: SocialLoginRequest): Authentication {
        val result = remote.socialLogin(request)
        local.saveUser(user = UserMapper.dtoToEntity(model = result.user ?: UserDto()))
        local.saveToken(token = result.accessToken.orEmpty())
        local.saveIsAuthenticated(true)
        return AuthenticationMapper.dtoToDomain(model = result)
    }
}