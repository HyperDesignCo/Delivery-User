package com.example.delivaryUser.feature.address.saveaddress.domain.model

import com.example.delivaryUser.service.address.domain.models.domain.Address

data class SaveAddress(
    val message: String,
    val address: Address
)
