package com.example.delivaryUser.feature.orders.base.domain.models.domain

data class Order(
    val id: Int,
    val userId: Int,
    val userName: String,
    val deliveryName: String,
    val deliveryImage: String,
    val orderStatus: OrderStatus,
    val note: String,
    val orderPrice: String,
    val deliveryPrice: String,
    val deliveryPhone: String,
    val clientAddress: String,
    val endAddress: String,
    val providerName: String,
    val clientName: String,
    val clientPhone: String,
    val clientLatitude: String,
    val clientLongitude: String,
    val endClientAddress: String,
    val endClientLatitude: String,
    val endClientLongitude: String,
    val deliveryLatitude: String,
    val deliveryLongitude: String,
    val chatId: String,
    val cancelReason: String,
    val rates: List<Rate>,
    val deliveryRate: String,
    val deliveryFees: String,
    val totalOrderPrice: Double,
    val createdAt: String,
    val orderPurpose: String
)

enum class OrderStatus(val value: String) {
    PENDING("pending"),
    ACCEPTED("accepted"),
    ON_WAY("on_way"),
    DELIVERY_ARRIVE("delivery_arrive"),
    DELIVERY_START("delivary_start"),
    DELIVERED("delivered"),
    CANCELED("canceled"),
    UNKNOWN("unknown")
}

fun String.toOrderStatus(): OrderStatus = OrderStatus.entries.find { it.value == this } ?: OrderStatus.UNKNOWN