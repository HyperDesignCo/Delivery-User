package com.example.delivaryUser.service.user.data.repository

import com.example.delivaryUser.service.user.data.mapper.UserMapper
import com.example.delivaryUser.service.user.domain.model.User
import com.example.delivaryUser.service.user.domain.repository.IUserRepository
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class UserRepository(private val local: IUserLocalDataSource) : IUserRepository {
    override suspend fun saveIsAuthenticated(isAuthenticated: Boolean) =
        local.saveIsAuthenticated(isAuthenticated)

    override suspend fun getIsAuthenticated(): Boolean =
        local.getIsAuthenticated()

    override suspend fun getUser(): User = UserMapper.entityToDomain(local.getUser())

    override suspend fun deleteToken() = local.deleteToken()

    override suspend fun deleteUser() = local.deleteUser()

    override suspend fun deleteRememberMe() = local.deleteRememberMe()

    override suspend fun deleteIsAuthenticated() = local.deleteIsAuthenticated()

    override suspend fun getPassword(): String = local.getPassword()
}