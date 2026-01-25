package com.example.delivaryUser.feature.orders.base.data.mappers

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.feature.orders.base.data.models.dto.RateDto
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Rate

object RateMapper : Mapper<RateDto, Rate, Unit>() {
    override fun dtoToDomain(model: RateDto): Rate = Rate(
        id = model.id.orEmpty(),
        rate = model.rate.orEmpty(),
        comment = model.comment.orEmpty(),
        createdAt = model.createdAt.orEmpty()
    )
}