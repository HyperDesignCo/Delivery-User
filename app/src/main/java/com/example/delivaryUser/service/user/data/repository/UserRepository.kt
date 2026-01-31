package com.example.delivaryUser.service.user.data.repository

import com.example.delivaryUser.service.user.domain.repository.IUserRepository
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource

class UserRepository(private val local: IUserLocalDataSource) : IUserRepository {
    override suspend fun saveIsAuthenticated(isAuthenticated: Boolean) =
        local.saveIsAuthenticated(isAuthenticated)

    override suspend fun getIsAuthenticated(): Boolean =
        local.getIsAuthenticated()
}