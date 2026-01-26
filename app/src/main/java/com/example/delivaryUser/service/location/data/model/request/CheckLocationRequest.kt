package com.example.delivaryUser.service.location.data.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckLocationRequest(
    @SerialName("lat")
    val latitude: String,
    @SerialName("lng")
    val longitude: String,
)
