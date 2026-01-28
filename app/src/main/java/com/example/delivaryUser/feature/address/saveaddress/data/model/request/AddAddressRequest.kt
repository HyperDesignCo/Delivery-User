package com.example.delivaryUser.feature.address.saveaddress.data.model.request

import com.example.delivaryUser.common.data.models.Const
import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddAddressRequest(
    @SerialName("name") val name: String?=null,
    @SerialName("phone1") val phone1: String,
    @SerialName("phone2") val phone2: String,
    @SerialName("country_id") val countryId: Int,
    @SerialName("region_id") val regionId: Int,
    @SerialName("area_id") val areaId: Int,
    @SerialName("building_number") val buildingNumber: String,
    @SerialName("apartment_number") val apartmentNumber: String,
    @SerialName("floor_number") val floorNumber: String,
    @SerialName("latitude") val latitude: String,
    @SerialName("longitude") val longitude: String,
    @SerialName("street") val street: String,
    @SerialName("special_sign") val specialSign: String
){

    fun isPhone1Valid(): Boolean {
        if (phone1.isBlank()) {
            return true
        }
        return Const.phoneRegex.matches(phone1)
    }
    fun isPhone2Valid(): Boolean {
        if (phone2.isBlank()) {
            return true
        }
        return Const.phoneRegex.matches(phone2)
    }

    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!isPhone1Valid()) errors[ErrorKeyEnum.PHONE_NUMBER] =
            RequestErrorKeyValues.PHONE_VALIDATION
        if (!isPhone2Valid()) errors[ErrorKeyEnum.PHONE_NUMBER] =
            RequestErrorKeyValues.PHONE_VALIDATION
        return errors
    }
}

