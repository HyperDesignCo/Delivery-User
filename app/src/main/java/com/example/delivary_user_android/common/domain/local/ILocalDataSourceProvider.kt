package com.example.delivary_user_android.common.domain.local

interface ILocalDataSourceProvider {
    suspend fun <Value> save(key: ILocalDataSourceEnum, value: Value, type: Class<*>)
    suspend fun <Value> read(key: ILocalDataSourceEnum, defaultValue: Value, type: Class<*>): Value
    suspend fun <Value> delete(key: ILocalDataSourceEnum, type: Class<*>)
}