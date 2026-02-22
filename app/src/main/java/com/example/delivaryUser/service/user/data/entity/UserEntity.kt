package com.example.delivaryUser.service.user.data.entity

import com.example.delivaryUser.service.user.domain.model.PhoneVerify
import com.example.delivaryUser.service.user.domain.model.UserStatus
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val id: Int = 0,
    val name: String= "",
    val phone: String = "",
    val email: String = "",
    val status: UserStatus = UserStatus.NOT_ACTIVE,
    val phoneVerify: PhoneVerify = PhoneVerify.NOT_VERIFIED,
    val image: String = "",
    val countryId: String = "",
    val regionId: String = "",
    val areaId: String = "",
    val deviceToken: String = "",
    val deviceType: String = "",
    val password : String = ""
)