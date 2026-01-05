package com.example.delivary_user_android.common.ui.loading

sealed interface ILoadingEvent {
    val isLoading : Boolean
    data class CircularProgressIndicator(override val isLoading: Boolean) : ILoadingEvent
}