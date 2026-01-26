package com.example.delivaryUser.service.location.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.service.location.data.model.dto.AddressDto
import com.example.delivaryUser.service.location.domain.model.Address

object AddressMapper : Mapper<AddressDto, Address, Unit>() {
    override fun dtoToDomain(model: AddressDto): Address =
        Address(
            currentArea = model.currentArea,
            currentRegion = model.currentRegion,
            currentAreaName = model.currentAreaName,
            currentRegionName = model.currentRegionName
        )
}