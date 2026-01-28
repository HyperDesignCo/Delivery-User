package com.example.delivaryUser.feature.orders.base.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("user_id")
    val userId: Int? = null,
    @SerialName("user_name")
    val userName: String? = null,
    @SerialName("delivery_name")
    val deliveryName: String? = null,
    @SerialName("delivery_rate")
    val deliveryRate: String? = null,
    @SerialName("delivery_image")
    val deliveryImage: String? = null,
    @SerialName("order_status")
    val orderStatus: String? = null,
    @SerialName("note")
    val note: String? = null,
    @SerialName("order_price")
    val orderPrice: String? = null,
    @SerialName("delivery_fees")
    val deliveryFees: String? = null,
    @SerialName("total_order_price")
    val totalOrderPrice: Double? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("delivery_price")
    val deliveryPrice: String? = null,
    @SerialName("client_address")
    val clientAddress: String? = null,
    @SerialName("end_address")
    val endAddress: String? = null,
    @SerialName("provider_name")
    val providerName: String? = null,
    @SerialName("client_name")
    val clientName: String? = null,
    @SerialName("client_phone")
    val clientPhone: String? = null,
    @SerialName("client_latitude")
    val clientLatitude: String? = null,
    @SerialName("client_longitude")
    val clientLongitude: String? = null,
    @SerialName("end_client_address")
    val endClientAddress: String? = null,
    @SerialName("end_client_latitude")
    val endClientLatitude: String? = null,
    @SerialName("end_client_longitude")
    val endClientLongitude: String? = null,
    @SerialName("delivery_latitude")
    val deliveryLatitude: String? = null,
    @SerialName("delivery_longitude")
    val deliveryLongitude: String? = null,
    @SerialName("delivery_phone")
    val deliveryPhone: String? = null,
    @SerialName("chat_id")
    val chatId: String? = null,
    @SerialName("cancel_reason")
    val cancelReason: String? = null,
    @SerialName("rates")
    val rates: List<RateDto>? = null,
    @SerialName("OrderPurpose")
    val orderPurpose: String? = null,
    )