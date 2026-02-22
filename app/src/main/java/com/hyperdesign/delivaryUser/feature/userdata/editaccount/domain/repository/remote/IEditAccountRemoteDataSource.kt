package com.hyperdesign.delivaryUser.feature.userdata.editaccount.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.userdata.editaccount.data.model.dto.EditAccountDto
import com.hyperdesign.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest

interface IEditAccountRemoteDataSource {
    suspend fun editAccount(request: EditAccountRequest) : EditAccountDto
}