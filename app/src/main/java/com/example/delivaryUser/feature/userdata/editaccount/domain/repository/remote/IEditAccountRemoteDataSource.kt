package com.example.delivaryUser.feature.userdata.editaccount.domain.repository.remote

import com.example.delivaryUser.feature.userdata.editaccount.data.model.dto.EditAccountDto
import com.example.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest

interface IEditAccountRemoteDataSource {
    suspend fun editAccount(request: EditAccountRequest) : EditAccountDto
}