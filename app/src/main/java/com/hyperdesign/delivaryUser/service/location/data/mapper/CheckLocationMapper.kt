package com.hyperdesign.delivaryUser.service.location.data.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.service.location.data.model.dto.CheckLocationDto
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import com.hyperdesign.delivaryUser.service.location.domain.model.Location


object CheckLocationMapper : Mapper<CheckLocationDto, CheckLocation, Unit>() {
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

}