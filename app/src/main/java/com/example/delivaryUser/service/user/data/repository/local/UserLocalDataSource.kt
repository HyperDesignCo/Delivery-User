package com.example.delivaryUser.service.user.data.repository.local

import com.example.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.example.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.example.delivaryUser.service.user.data.entity.UserEntity
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource
import kotlinx.serialization.json.Json

class UserLocalDataSource(private val localProvider: ILocalDataSourceProvider, private val json: Json) :
    IUserLocalDataSource {
    override suspend fun saveToken(token: String) =
        localProvider.save(key = LocalDataSourceEnum.ACCESS_TOKEN, value = token, type = String::class.java)

    override suspend fun getToken(): String = localProvider.read(
        key = LocalDataSourceEnum.ACCESS_TOKEN, defaultValue = "", type = String::class.java
    )

    override suspend fun saveUser(user: UserEntity) = localProvider.save(
        key = LocalDataSourceEnum.USER, value = json.encodeToString(value = user), type = String::class.java
    )

    override suspend fun getUser(): UserEntity = json.decodeFromString(
        string = localProvider.read(
            key = LocalDataSourceEnum.USER, defaultValue = "", type = String::class.java
        ), deserializer = UserEntity.serializer()
    )

    override suspend fun saveRememberMe(rememberMe: Boolean) {
        localProvider.save(
            key = LocalDataSourceEnum.REMEMBER_ME, value = rememberMe, type = Boolean::class.java
        )
    }

    override suspend fun getRememberMe(): Boolean =
        localProvider.read(key = LocalDataSourceEnum.REMEMBER_ME, defaultValue = false, type = Boolean::class.java)

    override suspend fun saveIsVerified(isVerified: Boolean) {
        localProvider.save(
            key = LocalDataSourceEnum.IS_VERIFIED, value = isVerified, type = Boolean::class.java
        )
    }

    override suspend fun getIsVerified(): Boolean =
        localProvider.read(key = LocalDataSourceEnum.IS_VERIFIED, defaultValue = false, type = Boolean::class.java)

    override suspend fun saveIsAuthenticated(isAuthenticated: Boolean) {
        localProvider.save(
            key = LocalDataSourceEnum.IS_AUTHENTICATED,
            value = isAuthenticated,
            type = Boolean::class.java
        )
    }

    override suspend fun getIsAuthenticated(): Boolean = localProvider.read(
        key = LocalDataSourceEnum.IS_AUTHENTICATED,
        defaultValue = false,
        type = Boolean::class.java
    )
}