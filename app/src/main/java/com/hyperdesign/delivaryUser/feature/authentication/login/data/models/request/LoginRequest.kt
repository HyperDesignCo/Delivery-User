package com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request

import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class LoginRequest(
    @SerialName("phone")
    val phone: String,
    @SerialName("password")
    val password: String,
    @SerialName("device_token")
    val deviceToken: String= "",
    @SerialName("device_type")
    val deviceType: String,
    @Transient
    val rememberMe: Boolean = false
) {
    private fun validatePassword() = password.length in 8..50

    private fun validatePhoneNumber() = phone.length in 9..15

    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validatePassword()) errors[ErrorKeyEnum.PASSWORD] =
            RequestErrorKeyValues.PASSWORD_VALIDATION

        if (!validatePhoneNumber()) errors[ErrorKeyEnum.PHONE_NUMBER] =
            RequestErrorKeyValues.PHONE_VALIDATION

        return errors
    }
}