package com.example.delivary_user_android.common.domain

import com.example.delivary_user_android.common.data.DelivaryUserException

sealed class Resource<out Model> {
    data class Success<out Model>(val model: Model) : Resource<Model>()
    data class Failure(val exception: DelivaryUserException) : Resource<Nothing>()
    data class Loading(val isLoading: Boolean = false) : Resource<Nothing>()
}