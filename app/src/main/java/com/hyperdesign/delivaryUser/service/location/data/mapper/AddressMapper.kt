package com.hyperdesign.delivaryUser.service.location.data.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.service.location.data.entity.LocationEntity
import com.hyperdesign.delivaryUser.service.location.data.model.dto.LocationDto
import com.hyperdesign.delivaryUser.service.location.domain.model.Location

object AddressMapper : Mapper<LocationDto, Location, LocationEntity>() {
    override fun dtoToDomain(model: LocationDto): Location = Location(
        currentArea = model.currentArea.orEmpty(),
        currentRegion = model.currentRegion.orEmpty(),
        currentAreaName = model.currentAreaName.orEmpty(),
        currentRegionName = model.currentRegionName.orEmpty(),
        currentAreaDeliveryCost = model.currentAreaCost.orEmpty()
    )


    override fun dtoToEntity(model: LocationDto): LocationEntity = LocationEntity(
        currentArea = model.currentArea.orEmpty(),
        currentRegion = model.currentRegion.orEmpty(),
        currentAreaName = model.currentAreaName.orEmpty(),
        currentAreaDeliveryCost = model.currentAreaCost.orEmpty(),
        currentRegionName = model.currentRegionName.orEmpty()
    )

    override fun entityToDomain(model: LocationEntity): Location = Location(
        currentArea = model.currentArea,
        currentRegion = model.currentRegion,
        currentAreaName = model.currentAreaName,
        currentRegionName = model.currentRegionName,
        currentAreaDeliveryCost = model.currentAreaDeliveryCost
    )
}