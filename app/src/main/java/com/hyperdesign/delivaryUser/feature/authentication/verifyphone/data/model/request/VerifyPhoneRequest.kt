package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.request

import com.hyperdesign.delivaryUser.common.data.models.Const
import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyPhoneRequest(
    @SerialName("phone")
    val phone: String,
) {
    private fun validatePhoneNumber(): Boolean = Const.phoneRegex.matches(phone)
    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validatePhoneNumber()) errors[ErrorKeyEnum.PHONE_NUMBER] =
            RequestErrorKeyValues.PHONE_VALIDATION
        return errors
    }
}