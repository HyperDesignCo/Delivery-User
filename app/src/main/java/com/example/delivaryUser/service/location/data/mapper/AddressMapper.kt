package com.example.delivaryUser.service.location.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.service.location.data.entity.LocationEntity
import com.example.delivaryUser.service.location.data.model.dto.LocationDto
import com.example.delivaryUser.service.location.domain.model.Location

object AddressMapper : Mapper<LocationDto, Location, LocationEntity>() {
    override fun dtoToDomain(model: LocationDto): Location =
        Location(
            currentArea = model.currentArea,
            currentRegion = model.currentRegion,
            currentAreaName = model.currentAreaName,
            currentRegionName = model.currentRegionName
        )


    override fun dtoToEntity(model: LocationDto): LocationEntity =
        LocationEntity(
            currentArea = model.currentArea ?: "",
            currentRegion = model.currentRegion ?: "",
            currentAreaName = model.currentAreaName ?: "",
            currentRegionName = model.currentRegionName ?: ""
        )

    override fun entityToDomain(model: LocationEntity): Location =
        Location(
            currentArea = model.currentArea,
            currentRegion = model.currentRegion,
            currentAreaName = model.currentAreaName,
            currentRegionName = model.currentRegionName
        )
}