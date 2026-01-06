package com.example.delivaryUser.service.user.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name : String ?= null,
    @SerialName("phone")
    val phone: String ? = null,
    @SerialName("email")
    val email : String ? = null,
    @SerialName("status")
    val status : String? = null,
    @SerialName("phone_verify")
    val phoneVerify: String? = null,
    @SerialName("image")
    val image : String ?= null,
    @SerialName("country_id")
    val countryId : String ?= null,
    @SerialName("region_id")
    val regionId : String ?= null,
    @SerialName("area_id")
    val areaId : String ?= null,
    @SerialName("device_token")
    val deviceToken : String ?= null,
    @SerialName("device_type")
    val deviceType : String ?= null,
    @SerialName("created_at")
    val createdAt: String ?= null,
    @SerialName("updated_at")
    val updatedAt : String ?= null
)