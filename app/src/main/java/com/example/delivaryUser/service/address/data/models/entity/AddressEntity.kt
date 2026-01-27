package com.example.delivaryUser.service.address.data.models.entity

import kotlinx.serialization.Serializable

@Serializable
data class AddressEntity(
    val id: Int,
    val phone1: String,
    val phone2: String,
    val street: String,
    val buildingNumber: String,
    val floorNumber: String,
    val apartmentNumber: String,
    val specialSign: String,
    val latitude: String,
    val longitude: String,
    val user: String,
    val area: String,
    val areaId: Int,
    val region: String,
    val regionId: Int,
    val countryId: String,
)