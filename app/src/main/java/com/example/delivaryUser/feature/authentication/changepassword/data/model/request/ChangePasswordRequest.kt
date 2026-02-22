package com.example.delivaryUser.feature.authentication.changepassword.data.model.request

import com.example.delivaryUser.common.data.models.Const
import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ChangePasswordRequest(
    @SerialName("phone")
    val phone: String = "",
    @SerialName("password")
    val password: String,
    @Transient
    val confirmPassword: String = "",
) {
    private fun validateNewPassword() = Const.passwordRegex.matches(password)
    private fun confirmationPassword() = password == confirmPassword
    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validateNewPassword()) errors[ErrorKeyEnum.PASSWORD] =
            RequestErrorKeyValues.PASSWORD_VALIDATION
        if (!confirmationPassword()) errors[ErrorKeyEnum.CONFIRMATION_PASSWORD] =
            RequestErrorKeyValues.CONFIRMATION_PASSWORD

        return errors
    }
}