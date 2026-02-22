package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.data.models.dto.OtpDto
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.domain.model.Otp

object  VerifyOtpMapper : Mapper<OtpDto, Otp, Unit>() {
    override fun dtoToDomain(model: OtpDto): Otp = Otp(message = model.message.orEmpty())
}