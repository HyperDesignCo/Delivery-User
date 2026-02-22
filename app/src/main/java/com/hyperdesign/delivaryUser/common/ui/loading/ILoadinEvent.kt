package com.hyperdesign.delivaryUser.common.ui.loading

sealed interface ILoadingEvent {
    val isLoading : Boolean
    data class CircularProgressIndicator(override val isLoading: Boolean) : ILoadingEvent
}