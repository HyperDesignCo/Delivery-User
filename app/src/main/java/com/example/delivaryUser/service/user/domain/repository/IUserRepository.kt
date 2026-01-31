package com.example.delivaryUser.service.user.domain.repository

interface IUserRepository {
    suspend fun saveIsAuthenticated(isAuthenticated: Boolean)
    suspend fun getIsAuthenticated(): Boolean
}