package com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeliveryCostRequest(
    @SerialName("address_id")
    val senderAddressId: String,
    @SerialName("end_address_id")
    val recipientAddressId: String,
)