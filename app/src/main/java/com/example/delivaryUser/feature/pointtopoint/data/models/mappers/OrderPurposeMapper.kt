package com.example.delivaryUser.feature.pointtopoint.data.models.mappers

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.feature.pointtopoint.data.models.dto.OrderPurposeDto
import com.example.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose

object OrderPurposeMapper : Mapper<OrderPurposeDto, OrderPurpose, Unit>() {
    override fun dtoToDomain(model: OrderPurposeDto): OrderPurpose = OrderPurpose(
        id = model.id.orEmpty(),
        name = model.name.orEmpty(),
        status = model.status.orEmpty()
    )
}