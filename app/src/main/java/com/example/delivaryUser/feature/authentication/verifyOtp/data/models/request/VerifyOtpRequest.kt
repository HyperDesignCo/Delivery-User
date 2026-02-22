package com.example.delivaryUser.feature.authentication.verifyOtp.data.models.request

import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequest(
    @SerialName("phone")
    val phone: String,
    @SerialName("code")
    val code: String,
) {
    fun validateCode(): Boolean = (code.length < 4).not()
    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validateCode()) errors[ErrorKeyEnum.OTP] =
            RequestErrorKeyValues.OTP_VALIDATION
        return errors
    }
}
