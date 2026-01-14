package com.example.delivaryUser.feature.authentication.forgetpassword.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.feature.authentication.forgetpassword.data.model.dto.ForgetPasswordDto
import com.example.delivaryUser.feature.authentication.forgetpassword.domain.model.domain.ForgetPassword

object ForgetPasswordMapper : Mapper<ForgetPasswordDto, ForgetPassword, Unit>() {
    override fun dtoToDomain(model: ForgetPasswordDto): ForgetPassword =
        ForgetPassword(message = model.message.orEmpty())
}