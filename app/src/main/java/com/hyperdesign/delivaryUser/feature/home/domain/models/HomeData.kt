package com.hyperdesign.delivaryUser.feature.home.domain.models

import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.Order

data class HomeData(
    val message: String,
    val ads: List<Ad>,
    val orders: List<Order>,
)