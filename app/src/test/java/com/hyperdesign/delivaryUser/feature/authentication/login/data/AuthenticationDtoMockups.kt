package com.hyperdesign.delivaryUser.feature.authentication.login.data

import com.hyperdesign.delivaryUser.feature.authentication.base.data.dto.AuthenticationDto
import com.hyperdesign.delivaryUser.feature.authentication.base.domain.model.Authentication
import com.hyperdesign.delivaryUser.feature.authentication.login.data.models.request.LoginRequest
import com.hyperdesign.delivaryUser.service.user.data.dto.UserDto

internal object AuthenticationDtoMockups {
    val validLoginRequest = LoginRequest(phone = "01111111111", password = "1234@alaA")


    val userDto = UserDto(
        id = 1,
        name = "Rodina Mo'men",
        phone = "01111111111",
        email = "rodina@example.com",
        status = "active",
        phoneVerify = "verified",
        image = "https://example.com/images/user.png",
        countryId = "1",
        regionId = "10",
        areaId = "100",
        deviceToken = "fcm_device_token_123456",
        deviceType = "android",
        createdAt = "2025-01-01T10:00:00Z",
        updatedAt = "2025-01-05T15:30:00Z"
    )
    val validLoginDto =
        AuthenticationDto(message = "success", user = userDto, code = "123", accessToken = "accessToken")
    val validLogin= Authentication(message = "success")
}