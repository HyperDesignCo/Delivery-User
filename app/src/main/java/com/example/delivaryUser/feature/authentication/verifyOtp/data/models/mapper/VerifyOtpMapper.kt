package com.example.delivaryUser.feature.authentication.verifyOtp.data.models.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.feature.authentication.verifyOtp.data.models.dto.OtpDto
import com.example.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp

object  VerifyOtpMapper : Mapper<OtpDto, Otp, Unit>() {
    override fun dtoToDomain(model: OtpDto): Otp = Otp(message = model.message.orEmpty())
}