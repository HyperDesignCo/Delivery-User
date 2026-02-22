package com.example.delivaryUser.feature.authentication.changepassword.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.example.delivaryUser.feature.authentication.changepassword.data.model.request.ChangePasswordRequest
import com.example.delivaryUser.feature.authentication.changepassword.domain.repository.remote.IChangePasswordRemoteDataSource

class ChangePasswordRemoteDataSource(private val provider: IRemoteDataSourceProvider) :
    IChangePasswordRemoteDataSource {
    override suspend fun changePassword(
        request: ChangePasswordRequest,
    ): AuthenticationDto = provider.post(
        endpoint = CHANGE_PASSWORD_ENDPOINT,
        requestBody = request,
        serializer = AuthenticationDto.serializer()
    )

    companion object {
        const val CHANGE_PASSWORD_ENDPOINT = "User_new_password"
    }
}