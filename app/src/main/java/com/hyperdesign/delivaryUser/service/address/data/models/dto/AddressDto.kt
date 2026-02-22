package com.hyperdesign.delivaryUser.service.address.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("phone1")
    val firstPhone: String? = null,
    @SerialName("phone2")
    val secondPhone: String? = null,
    @SerialName("street")
    val street: String? = null,
    @SerialName("building_number")
    val buildingNumber: String? = null,
    @SerialName("floor_number")
    val floorNumber: String? = null,
    @SerialName("apartment_number")
    val apartmentNumber: String? = null,
    @SerialName("special_sign")
    val specialSign: String? = null,
    @SerialName("latitude")
    val latitude: String? = null,
    @SerialName("longitude")
    val longitude: String? = null,
    @SerialName("user")
    val user: String? = null,
    @SerialName("area")
    val area: String? = null,
    @SerialName("area_id")
    val areaId: String? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("region_id")
    val regionId: String? = null,
    @SerialName("country_id")
    val countryId: String? = null,
)