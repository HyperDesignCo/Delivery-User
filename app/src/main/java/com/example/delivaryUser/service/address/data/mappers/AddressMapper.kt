package com.example.delivaryUser.service.address.data.mappers

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.service.address.data.models.dto.AddressDto
import com.example.delivaryUser.service.address.data.models.entity.AddressEntity
import com.example.delivaryUser.service.address.domain.models.domain.Address


object AddressMapper : Mapper<AddressDto, Address, AddressEntity>() {
    override fun dtoToDomain(model: AddressDto): Address = Address(
        id = model.id.orEmpty(),
        phone1 = model.phone1.orEmpty(),
        phone2 = model.phone2.orEmpty(),
        street = model.street.orEmpty(),
        buildingNumber = model.buildingNumber.orEmpty(),
        floorNumber = model.floorNumber.orEmpty(),
        apartmentNumber = model.apartmentNumber.orEmpty(),
        specialSign = model.specialSign.orEmpty(),
        latitude = model.latitude.orEmpty(),
        longitude = model.longitude.orEmpty(),
        user = model.user.orEmpty(),
        area = model.area.orEmpty(),
        areaId = model.areaId.orEmpty(),
        region = model.region.orEmpty(),
        regionId = model.regionId.orEmpty(),
        countryId = model.countryId.orEmpty(),
    )

    override fun domainToEntity(model: Address): AddressEntity = AddressEntity(
        id = model.id,
        phone1 = model.phone1,
        phone2 = model.phone2,
        street = model.street,
        buildingNumber = model.buildingNumber,
        floorNumber = model.floorNumber,
        apartmentNumber = model.apartmentNumber,
        specialSign = model.specialSign,
        latitude = model.latitude,
        longitude = model.longitude,
        user = model.user,
        area = model.area,
        areaId = model.areaId,
        region = model.region,
        regionId = model.regionId,
        countryId = model.countryId,
    )

    override fun entityToDomain(model: AddressEntity): Address = Address(
        id = model.id.orEmpty(),
        phone1 = model.phone1,
        phone2 = model.phone2,
        street = model.street,
        buildingNumber = model.buildingNumber,
        floorNumber = model.floorNumber,
        apartmentNumber = model.apartmentNumber,
        specialSign = model.specialSign,
        latitude = model.latitude,
        longitude = model.longitude,
        user = model.user,
        area = model.area,
        areaId = model.areaId,
        region = model.region,
        regionId = model.regionId,
        countryId = model.countryId,
    )
}