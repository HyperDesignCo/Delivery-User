package com.example.delivary_user_android.common.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("errors")
    val errors: Map<ErrorKeyEnum, List<String>>?,
)