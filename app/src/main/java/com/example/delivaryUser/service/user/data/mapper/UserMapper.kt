package com.example.delivaryUser.service.user.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.service.user.data.dto.UserDto
import com.example.delivaryUser.service.user.data.entity.UserEntity
import com.example.delivaryUser.service.user.domain.model.User
import com.example.delivaryUser.service.user.domain.model.toPhoneVerify
import com.example.delivaryUser.service.user.domain.model.toUserStatus
import kotlin.text.orEmpty

object UserMapper : Mapper<UserDto, User, UserEntity>() {
    override fun dtoToDomain(model: UserDto): User = User(
        id = model.id.orEmpty(),
        name = model.name.orEmpty(),
        phone = model.phone.orEmpty(),
        email = model.email.orEmpty(),
        status = model.status.orEmpty().toUserStatus(),
        phoneVerify = model.phoneVerify.orEmpty().toPhoneVerify(),
        image = model.image.orEmpty(),
        countryId = model.countryId.orEmpty(),
        regionId = model.regionId.orEmpty(),
        areaId = model.areaId.orEmpty(),
    )

    override fun dtoToEntity(model: UserDto): UserEntity = UserEntity(
        id = model.id.orEmpty(),
        name = model.name.orEmpty(),
        phone = model.phone.orEmpty(),
        email = model.email.orEmpty(),
        status = model.status.orEmpty().toUserStatus(),
        phoneVerify = model.phoneVerify.orEmpty().toPhoneVerify(),
        image = model.image.orEmpty(),
        countryId = model.countryId.orEmpty(),
        regionId = model.regionId.orEmpty(),
        areaId = model.areaId.orEmpty(),
        deviceType = model.deviceType.orEmpty(),
        deviceToken = model.deviceToken.orEmpty()
    )

    override fun entityToDomain(model: UserEntity): User = User(
        id = model.id.orEmpty(),
        name = model.name,
        phone = model.phone,
        email = model.email,
        status = model.status,
        phoneVerify = model.phoneVerify,
        image = model.image,
        countryId = model.countryId,
        regionId = model.regionId,
        areaId = model.areaId,
        password = model.password
    )

    override fun domainToEntity(model: User): UserEntity = UserEntity(
            id = model.id,
            name = model.name,
            phone = model.phone,
            email = model.email,
            status = model.status,
            phoneVerify = model.phoneVerify,
            image = model.image,
            countryId = model.countryId,
            regionId = model.regionId,
            areaId = model.areaId,
            password = model.password
        )

}