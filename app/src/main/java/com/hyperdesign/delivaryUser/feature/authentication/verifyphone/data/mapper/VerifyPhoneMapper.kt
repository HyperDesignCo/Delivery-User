package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.data.model.dto.VerifyPhoneDto

object VerifyPhoneMapper : Mapper<VerifyPhoneDto, String, Unit>() {
    override fun dtoToDomain(model: VerifyPhoneDto): String =
        model.message.orEmpty()
}
