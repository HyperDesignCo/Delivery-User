package com.example.delivary_user_android.common.data.loocal

import com.example.delivary_user_android.common.domain.local.ILocalDataSourceEnum

enum class LocalDataSourceEnum(override val keyValue: String) : ILocalDataSourceEnum {
    ACCESS_TOKEN("access_token"),
    USER("user"),
    COUNTRIES("country"),
    SELECTED_COUNTRY("selected_country"),
    LANGUAGE("language"),
    ONBOARDING_STATUS("onboarding_status"),
    IS_AUTHENTICATED("is_authenticated");
}