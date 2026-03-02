package com.hyperdesign.delivaryUser.feature.authentication.register.data.models.request

import android.util.Patterns
import com.hyperdesign.delivaryUser.common.data.models.Const
import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("phone")
    val phone: String,
    @SerialName("password")
    val password: String,
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
) {
    private fun validatePhoneNumber(): Boolean = Const.phoneRegex.matches(phone)

    private fun validatePassword(): Boolean = Const.passwordRegex.matches(password)

    private fun validateEmail(): Boolean =
        when {
            email.isEmpty() -> true
            Patterns.EMAIL_ADDRESS.matcher(email).matches() -> true
            else -> false
        }

    private fun validateName(): Boolean = Const.nameRegex.matches(name)

    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validatePassword()) errors[ErrorKeyEnum.PASSWORD] =
            RequestErrorKeyValues.PASSWORD_VALIDATION
        if (!validatePhoneNumber()) errors[ErrorKeyEnum.PHONE_NUMBER] =
            RequestErrorKeyValues.PHONE_VALIDATION
        if (!validateName()) errors[ErrorKeyEnum.NAME] =
            RequestErrorKeyValues.NAME_VALIDATION
        if (!validateEmail()) errors[ErrorKeyEnum.EMAIL] =
            RequestErrorKeyValues.EMAIL_VALIDATION
        return errors
    }
}