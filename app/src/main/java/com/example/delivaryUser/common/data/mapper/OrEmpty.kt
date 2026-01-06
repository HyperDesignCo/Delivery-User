package com.example.delivaryUser.common.data.mapper

fun Int?.orEmpty() = this ?: 0
fun Float?.orEmpty() = this ?: 0.0
fun Double?.orEmpty() = this ?: 0.0
fun Long?.orEmpty() = this ?: 0L