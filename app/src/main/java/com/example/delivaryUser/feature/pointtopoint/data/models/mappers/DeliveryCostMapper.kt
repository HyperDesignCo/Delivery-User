package com.example.delivaryUser.feature.pointtopoint.data.models.mappers

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.feature.pointtopoint.data.models.dto.DeliveryCostResponseDto
import com.example.delivaryUser.feature.pointtopoint.domain.model.DeliveryCost

object DeliveryCostMapper : Mapper<DeliveryCostResponseDto, DeliveryCost, Unit>() {
    override fun dtoToDomain(model: DeliveryCostResponseDto): DeliveryCost = DeliveryCost(
        deliveryCost = model.deliveryCost.orEmpty(),
        distance = model.distance.orEmpty()
    )
}