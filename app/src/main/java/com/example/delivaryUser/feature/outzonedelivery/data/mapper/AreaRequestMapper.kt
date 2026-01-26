package com.example.delivaryUser.feature.outzonedelivery.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.feature.outzonedelivery.data.model.dto.AreaRequestDto
import com.example.delivaryUser.feature.outzonedelivery.domain.model.AreaRequest

object AreaRequestMapper : Mapper<AreaRequestDto, AreaRequest, Unit>() {

    override fun dtoToDomain(model: AreaRequestDto): AreaRequest =
        AreaRequest(
            latitude = model.latitude,
            longitude = model.longitude,
            updatedAt = model.updatedAt,
            createdAt = model.createdAt,
            id = model.id
        )
}