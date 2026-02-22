package com.example.delivaryUser.feature.home.domain.models

import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order

data class HomeData(
    val message: String,
    val ads: List<Ad>,
    val orders: List<Order>,
)