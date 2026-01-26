package com.example.delivaryUser.service.location.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.service.location.data.model.dto.CheckLocationDto
import com.example.delivaryUser.service.location.domain.model.CheckLocation

object CheckLocationMapper: Mapper<CheckLocationDto, CheckLocation, Unit>(){
    override fun dtoToDomain(model: CheckLocationDto): CheckLocation =
        CheckLocation(message = model.message, data = model.data?.let { AddressMapper.dtoToDomain(it) })
}

