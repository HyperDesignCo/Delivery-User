package com.hyperdesign.delivaryUser.feature.userdata.editaccount.domain.repository

import com.hyperdesign.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest

interface IEditAccountRepository {
    suspend fun editAccount(request: EditAccountRequest)
}