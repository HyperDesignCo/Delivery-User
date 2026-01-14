package com.example.delivaryUser.service.user.domain.repository.local

import com.example.delivaryUser.service.user.data.entity.UserEntity

interface IUserLocalDataSource {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): UserEntity
    suspend fun saveRememberMe(rememberMe: Boolean)
    suspend fun getRememberMe(): Boolean
    suspend fun saveIsVerified(isVerified: Boolean)
    suspend fun getIsVerified(): Boolean
}