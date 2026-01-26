package com.example.delivaryUser.feature.outzonedelivery.domain.model

data class AddAreaResponse(
    val message: String,
    val areaRequest: AreaRequest? = null
)

data class AreaRequest(
    val latitude: String,
    val longitude: String,
    val updatedAt: String,
    val createdAt: String,
    val id: Int
)
