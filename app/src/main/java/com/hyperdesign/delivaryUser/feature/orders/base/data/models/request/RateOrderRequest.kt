package com.hyperdesign.delivaryUser.feature.orders.base.data.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateOrderRequest(
    @SerialName("order_id")
    val orderId : Int,
    @SerialName("rate")
    val rate : Int ,
    @SerialName("comment")
    val comment :String ,
)