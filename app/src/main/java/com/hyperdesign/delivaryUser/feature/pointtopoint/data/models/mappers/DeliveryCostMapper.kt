package com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.mappers

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.common.data.mapper.orEmpty
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.dto.DeliveryCostResponseDto
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost

object DeliveryCostMapper : Mapper<DeliveryCostResponseDto, DeliveryCost, Unit>() {
    override fun dtoToDomain(model: DeliveryCostResponseDto): DeliveryCost = DeliveryCost(
        deliveryCost = model.deliveryCost.orEmpty(),
        distance = model.distance.orEmpty()
    )
}