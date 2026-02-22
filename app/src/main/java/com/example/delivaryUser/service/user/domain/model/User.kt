package com.example.delivaryUser.service.user.domain.model

import kotlinx.serialization.Serializable

data class User(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val status: UserStatus,
    val phoneVerify: PhoneVerify,
    val image: String,
    val countryId: String,
    val regionId: String,
    val areaId: String,
    val password: String= "",
)

@Serializable
enum class UserStatus() {
    ACTIVE,
    NOT_ACTIVE,
}

fun String.toUserStatus(): UserStatus = if (this == "1") UserStatus.ACTIVE else UserStatus.NOT_ACTIVE

@Serializable
enum class PhoneVerify() {
    VERIFIED,
    NOT_VERIFIED,
}

fun String.toPhoneVerify(): PhoneVerify = if (this == "1") PhoneVerify.VERIFIED else PhoneVerify.NOT_VERIFIED