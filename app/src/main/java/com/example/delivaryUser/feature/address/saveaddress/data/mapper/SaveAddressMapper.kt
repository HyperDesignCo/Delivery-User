package com.example.delivaryUser.feature.address.saveaddress.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.feature.address.saveaddress.data.model.dto.SaveAddressDto
import com.example.delivaryUser.feature.address.saveaddress.domain.model.SaveAddress
import com.example.delivaryUser.service.address.data.mappers.AddressMapper

object SaveAddressMapper: Mapper<SaveAddressDto, SaveAddress, Unit>() {

    override fun dtoToDomain(model: SaveAddressDto): SaveAddress =
        SaveAddress(
            message = model.message,
            address = AddressMapper.dtoToDomain(model.address)
        )
}