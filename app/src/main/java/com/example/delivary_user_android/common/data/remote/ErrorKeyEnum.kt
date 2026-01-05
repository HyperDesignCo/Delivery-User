package com.example.delivary_user_android.common.data.remote

import com.example.delivary_user_android.common.domain.exceptions.IErrorKeyEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ErrorKeyEnum : IErrorKeyEnum {
    @SerialName("phone")
    PHONE_NUMBER,
    @SerialName("email")
    EMAIL,
    UNKNOWN;
}