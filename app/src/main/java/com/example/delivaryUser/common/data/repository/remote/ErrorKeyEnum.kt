package com.example.delivaryUser.common.data.repository.remote

import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ErrorKeyEnum : IErrorKeyEnum {
    @SerialName("phone")
    PHONE_NUMBER,

    @SerialName("email")
    EMAIL,

    @SerialName("password")
    PASSWORD,

    @SerialName("name")
    NAME,

    @SerialName("code")
    OTP,

    @SerialName("confirmationPassword")
    CONFIRMATION_PASSWORD,

    @SerialName("phone1")
    ADDRESS_FIRST_PHONE,

    @SerialName("phone2")
    ADDRESS_SECOND_PHONE,
    UNKNOWN;
}