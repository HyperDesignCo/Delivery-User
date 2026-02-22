package com.example.delivaryUser.feature.authentication.base.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.example.delivaryUser.feature.authentication.base.domain.model.Authentication

object AuthenticationMapper : Mapper<AuthenticationDto, Authentication, Unit>() {

    override fun dtoToDomain(model: AuthenticationDto): Authentication =
        Authentication(message = model.message.orEmpty())
}