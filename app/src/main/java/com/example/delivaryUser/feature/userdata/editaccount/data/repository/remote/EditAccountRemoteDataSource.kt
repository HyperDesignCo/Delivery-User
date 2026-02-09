package com.example.delivaryUser.feature.userdata.editaccount.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.userdata.editaccount.data.model.dto.EditAccountDto
import com.example.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest
import com.example.delivaryUser.feature.userdata.editaccount.domain.repository.remote.IEditAccountRemoteDataSource

class EditAccountRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IEditAccountRemoteDataSource {
    override suspend fun editAccount(request: EditAccountRequest): EditAccountDto =
        provider.postWithFile(
            endpoint = EDIT_ACCOUNT_ENDPOINT,
            files = request.remoteRequestWithFiles,
            requestBody = request.remoteRequest,
            serializer = EditAccountDto.serializer()
        )

    companion object {
        const val EDIT_ACCOUNT_ENDPOINT = "UserUpdateProfile"
    }
}