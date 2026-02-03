package com.example.delivaryUser.feature.userdata.editaccount.domain.repository

import com.example.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest

interface IEditAccountRepository {
    suspend fun editAccount(request: EditAccountRequest)
}