package com.example.delivaryUser.feature.pointtopoint.data.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointToPointRequest(
    @SerialName("order_purpose_id")
    val orderPurposeId: Int,
    @SerialName("address_id")
    val senderAddressId: Int,
    @SerialName("end_address_id")
    val recipientAddressId: Int,
    @SerialName("note")
    val note: String,
    @SerialName("price")
    val orderEstimatedPrice: Double,
    @SerialName("delivery_cost")
    val deliveryCost: Double,
    @SerialName("order_type")
    val orderType: String,
)