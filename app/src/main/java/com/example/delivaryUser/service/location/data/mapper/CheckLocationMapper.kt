package com.example.delivaryUser.service.location.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.service.location.data.entity.CheckLocationEntity
import com.example.delivaryUser.service.location.data.entity.LocationEntity
import com.example.delivaryUser.service.location.data.model.dto.CheckLocationDto
import com.example.delivaryUser.service.location.domain.model.CheckLocation

object CheckLocationMapper: Mapper<CheckLocationDto, CheckLocation, CheckLocationEntity>(){
    override fun dtoToDomain(model: CheckLocationDto): CheckLocation =
        CheckLocation(message = model.message, data = model.data?.let { AddressMapper.dtoToDomain(it) })

    override fun dtoToEntity(model: CheckLocationDto): CheckLocationEntity =
        CheckLocationEntity(message = model.message, data = model.data?.let { AddressMapper.dtoToEntity(it) } as LocationEntity)

    override fun entityToDomain(model: CheckLocationEntity): CheckLocation =
        CheckLocation(message = model.message, data = model.data.let { AddressMapper.entityToDomain(it) })
}