package com.hyperdesign.delivaryUser.service.user.data.repository.local

import android.util.Base64
import com.hyperdesign.delivaryUser.common.data.models.Crypto
import com.hyperdesign.delivaryUser.common.data.repository.local.LocalDataSourceEnum
import com.hyperdesign.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.hyperdesign.delivaryUser.service.user.data.entity.UserEntity
import com.hyperdesign.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource
import kotlinx.serialization.json.Json

class UserLocalDataSource(private val localProvider: ILocalDataSourceProvider, private val json: Json) :
    IUserLocalDataSource {
    override suspend fun saveToken(token: String) {
        val encryptedBytes = Crypto.encrypt(token.toByteArray(Charsets.UTF_8))
        val encryptedString = Base64.encodeToString(encryptedBytes, Base64.NO_WRAP)
        localProvider.save(
            key = LocalDataSourceEnum.ACCESS_TOKEN,
            value = encryptedString,
            type = String::class.java
        )
    }

    override suspend fun getToken(): String {
        val encryptedString = localProvider.read(
            key = LocalDataSourceEnum.ACCESS_TOKEN,
            defaultValue = "",
            type = String::class.java
        )
        if (encryptedString.isEmpty()) return ""
        val encryptedBytes = Base64.decode(encryptedString, Base64.NO_WRAP)
        val decryptedBytes = Crypto.decrypt(encryptedBytes)
        return String(decryptedBytes, Charsets.UTF_8)
    }

    override suspend fun savePassword(password: String) {
        val encryptedBytes = Crypto.encrypt(password.toByteArray(Charsets.UTF_8))
        val encryptedString = Base64.encodeToString(encryptedBytes, Base64.NO_WRAP)
        localProvider.save(key = LocalDataSourceEnum.PASSWORD, value = encryptedString, type = String::class.java)
    }

    override suspend fun getPassword(): String {
        val encryptedString =
            localProvider.read(key = LocalDataSourceEnum.PASSWORD, defaultValue = "", type = String::class.java)
        if (encryptedString.isEmpty()) return ""
        val encryptedBytes = Base64.decode(encryptedString, Base64.NO_WRAP)
        val decryptedBytes = Crypto.decrypt(encryptedBytes)
        return String(decryptedBytes, Charsets.UTF_8)
    }

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

    override suspend fun deleteToken() =
        localProvider.delete<String>(key = LocalDataSourceEnum.ACCESS_TOKEN, type = String::class.java)

    override suspend fun deleteUser() =
        localProvider.delete<UserEntity>(key = LocalDataSourceEnum.USER, type = String::class.java)

    override suspend fun deleteRememberMe() =
        localProvider.delete<Boolean>(key = LocalDataSourceEnum.REMEMBER_ME, type = Boolean::class.java)

    override suspend fun deleteIsAuthenticated() =
        localProvider.delete<Boolean>(key = LocalDataSourceEnum.IS_AUTHENTICATED, type = Boolean::class.java)
}