package com.example.delivaryUser.common.data.repository.local

import com.example.delivaryUser.common.domain.local.ILocalDataSourceEnum

enum class LocalDataSourceEnum(override val keyValue: String) : ILocalDataSourceEnum {
    ACCESS_TOKEN("access_token"),
    USER("user"),
    REMEMBER_ME("remember_me"),
    IS_VERIFIED("is_verified"),
    SENDER_ADDRESS("sender_address"),
    RECIPIENT_ADDRESS("recipient_address"),
    LANGUAGE("language"),
    IS_AUTHENTICATED("is_authenticated"),
    PASSWORD("password"),
    SAVED_LOCATION("saved_location"),
    FIRST_LAUNCH_COMPLETE("first_launch_complete"),
    CHECK_LOCATION_RESPONSE("check_location_response")
}