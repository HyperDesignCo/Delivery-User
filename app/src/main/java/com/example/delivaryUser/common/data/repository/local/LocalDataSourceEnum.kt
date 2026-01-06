package com.example.delivaryUser.common.data.repository.local

import com.example.delivaryUser.common.domain.local.ILocalDataSourceEnum

enum class LocalDataSourceEnum(override val keyValue: String) : ILocalDataSourceEnum {
    ACCESS_TOKEN("access_token"),
    USER("user"),
}