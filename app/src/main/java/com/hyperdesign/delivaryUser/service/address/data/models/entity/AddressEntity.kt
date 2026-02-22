package com.hyperdesign.delivaryUser.service.address.data.models.entity

import kotlinx.serialization.Serializable

@Serializable
data class AddressEntity(
    val id: Int = 0,
    val firstPhone: String= "",
    val secondPhone: String= "",
    val street: String= "",
    val buildingNumber: String= "",
    val floorNumber: String= "",
    val apartmentNumber: String= "",
    val specialSign: String= "",
    val latitude: String= "",
    val longitude: String= "",
    val user: String= "",
    val area: String= "",
    val areaId: String="",
    val region: String= "",
    val regionId: String= "",
    val countryId: String= "",
)