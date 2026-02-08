package com.example.delivaryUser.service.location.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.service.location.data.entity.CheckLocationEntity
import com.example.delivaryUser.service.location.data.entity.LocationEntity
import com.example.delivaryUser.service.location.data.model.dto.CheckLocationDto
import com.example.delivaryUser.service.location.domain.model.CheckLocation
import com.example.delivaryUser.service.location.domain.model.Location

object CheckLocationMapper : Mapper<CheckLocationDto, CheckLocation, CheckLocationEntity>() {
    override fun dtoToDomain(model: CheckLocationDto): CheckLocation =
        CheckLocation(
            message = model.message.orEmpty(), data = Location(
                currentArea = model.data?.currentArea.orEmpty(),
                currentAreaName = model.data?.currentAreaName.orEmpty(),
                currentAreaDeliveryCost = model.data?.currentAreaCost.orEmpty(),
                currentRegion = model.data?.currentRegion.orEmpty(),
                currentRegionName = model.data?.currentRegion.orEmpty()
            )
        )

    override fun dtoToEntity(model: CheckLocationDto): CheckLocationEntity =
        CheckLocationEntity(
            message = model.message.orEmpty(),
            data = model.data?.let { AddressMapper.dtoToEntity(it) } as LocationEntity)

    override fun entityToDomain(model: CheckLocationEntity): CheckLocation =
        CheckLocation(message = model.message, data = model.data.let { AddressMapper.entityToDomain(it) })
}