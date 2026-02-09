package com.example.delivaryUser.service.user.domain.repository

import com.example.delivaryUser.service.user.domain.model.User

interface IUserRepository {
    suspend fun saveIsAuthenticated(isAuthenticated: Boolean)
    suspend fun getIsAuthenticated(): Boolean
    suspend fun getUser(): User
    suspend fun deleteToken()
    suspend fun deleteUser()
    suspend fun deleteRememberMe()
    suspend fun deleteIsAuthenticated()
    suspend fun getPassword(): String
    suspend fun saveToken(token: String)
    suspend fun getToken(): String
}