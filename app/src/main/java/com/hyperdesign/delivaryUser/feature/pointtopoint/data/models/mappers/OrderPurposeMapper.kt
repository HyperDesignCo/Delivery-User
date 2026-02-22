package com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.mappers

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.common.data.mapper.orEmpty
import com.hyperdesign.delivaryUser.feature.pointtopoint.data.models.dto.OrderPurposeDto
import com.hyperdesign.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose

object OrderPurposeMapper : Mapper<OrderPurposeDto, OrderPurpose, Unit>() {
    override fun dtoToDomain(model: OrderPurposeDto): OrderPurpose = OrderPurpose(
        id = model.id.orEmpty(),
        name = model.name.orEmpty(),
        status = model.status.orEmpty()
    )
}