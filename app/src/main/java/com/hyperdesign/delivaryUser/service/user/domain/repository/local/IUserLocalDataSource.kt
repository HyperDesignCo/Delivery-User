package com.hyperdesign.delivaryUser.service.user.domain.repository.local

import com.hyperdesign.delivaryUser.service.user.data.entity.UserEntity

interface IUserLocalDataSource {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): UserEntity
    suspend fun saveRememberMe(rememberMe: Boolean)
    suspend fun getRememberMe(): Boolean
    suspend fun saveIsVerified(isVerified: Boolean)
    suspend fun getIsVerified(): Boolean
    suspend fun saveIsAuthenticated(isAuthenticated: Boolean)
    suspend fun getIsAuthenticated(): Boolean
    suspend fun deleteToken()
    suspend fun deleteUser()
    suspend fun deleteRememberMe()
    suspend fun deleteIsAuthenticated()
    suspend fun savePassword(password : String)
    suspend fun getPassword(): String
}