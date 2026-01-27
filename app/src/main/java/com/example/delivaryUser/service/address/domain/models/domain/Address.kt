package com.example.delivaryUser.service.address.domain.models.domain

import kotlinx.serialization.Serializable

@Serializable
data class Address(
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