package com.example.delivaryUser.feature.outzonedelivery.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.feature.outzonedelivery.data.model.dto.AddAreaResponseDto
import com.example.delivaryUser.feature.outzonedelivery.domain.model.AddAreaResponse

object AddAreaResponseMapper : Mapper<AddAreaResponseDto, AddAreaResponse, Unit>() {

    override fun dtoToDomain(model: AddAreaResponseDto): AddAreaResponse =
        AddAreaResponse(
            message = model.message,
            areaRequest = model.areaRequest?.let { AreaRequestMapper.dtoToDomain(it) })
}