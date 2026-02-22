package com.hyperdesign.delivaryUser.feature.orders.base.data.mappers

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.common.data.mapper.orEmpty
import com.hyperdesign.delivaryUser.feature.orders.base.data.models.dto.OrderImageDto
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.OrderImage

object OrderImageMapper : Mapper<OrderImageDto, OrderImage, Unit>() {
    override fun dtoToDomain(model: OrderImageDto): OrderImage =
        OrderImage(id = model.id.orEmpty(), orderId = model.orderId.orEmpty(), image = model.image.orEmpty())
}