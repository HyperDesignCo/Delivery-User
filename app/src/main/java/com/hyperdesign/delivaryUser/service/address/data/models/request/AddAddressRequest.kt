package com.hyperdesign.delivaryUser.service.address.data.models.request

import com.hyperdesign.delivaryUser.common.data.models.Const
import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddAddressRequest(
    @SerialName("name") val name: String? = null,
    @SerialName("phone1") val firstPhoneNumber: String,
    @SerialName("phone2") val secondPhoneNumber: String,
    @SerialName("country_id") val countryId: Int,
    @SerialName("region_id") val regionId: Int,
    @SerialName("area_id") val areaId: Int,
    @SerialName("building_number") val buildingNumber: String,
    @SerialName("apartment_number") val apartmentNumber: String,
    @SerialName("floor_number") val floorNumber: String,
    @SerialName("latitude") val latitude: String,
    @SerialName("longitude") val longitude: String,
    @SerialName("street") val street: String,
    @SerialName("special_sign") val specialSign: String,
) {
    fun validateFirstPhoneNumber(): Boolean = Const.optioinalPhoneRegex.matches(firstPhoneNumber)

    fun validateSecondPhoneNumber(): Boolean = Const.optioinalPhoneRegex.matches(secondPhoneNumber)
    fun validateFirstAndSecondPhoneNumbersDifferent(): Boolean =
       Const.optioinalPhoneRegex.matches(secondPhoneNumber)

    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validateFirstPhoneNumber()) errors[ErrorKeyEnum.ADDRESS_FIRST_PHONE] =
            RequestErrorKeyValues.ADDRESS_FIRST_PHONE_VALIDATION
        if (!validateSecondPhoneNumber()) errors[ErrorKeyEnum.ADDRESS_SECOND_PHONE] =
            RequestErrorKeyValues.ADDRESS_SECOND_PHONE_VALIDATION
        if (!validateFirstAndSecondPhoneNumbersDifferent()) errors[ErrorKeyEnum.ADDRESS_SECOND_PHONE] =
            RequestErrorKeyValues.ADDRESS_SECOND_PHONE_VALIDATION
        return errors
    }
}

