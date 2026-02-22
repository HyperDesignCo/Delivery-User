package com.hyperdesign.delivaryUser.feature.userdata.editaccount.data.model.request

import com.hyperdesign.delivaryUser.common.data.models.Const
import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.data.repository.remote.File
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.RequestErrorKeyValues
import com.hyperdesign.delivaryUser.common.domain.remote.IFile

data class EditAccountRequest(
    val name: String,
    val phone: String,
    val image: File? = null,
) {
    val remoteRequest = hashMapOf<String, Any>().apply {
        put("name", name)
        put("phone", phone)
    }
    val remoteRequestWithFiles = mutableListOf<Pair<String, IFile>>().apply {
        image?.let { add("image" to it) }
    }

    private fun validatePhoneNumber(): Boolean = Const.phoneRegex.matches(phone.trim())
    private fun validateName(): Boolean = Const.nameRegex.matches(name)

    fun validateFields(): Map<IErrorKeyEnum, RequestErrorKeyValues> {
        val errors = mutableMapOf<IErrorKeyEnum, RequestErrorKeyValues>()
        if (!validatePhoneNumber()) errors[ErrorKeyEnum.PHONE_NUMBER] = RequestErrorKeyValues.PHONE_VALIDATION
        if (!validateName()) errors[ErrorKeyEnum.NAME] = RequestErrorKeyValues.NAME_VALIDATION
        return errors
    }
}
