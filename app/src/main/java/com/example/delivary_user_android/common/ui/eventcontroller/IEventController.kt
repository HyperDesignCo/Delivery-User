package com.example.delivary_user_android.common.ui.eventcontroller

import kotlinx.coroutines.flow.Flow

interface IEventController<Event> {
    val event: Flow<Event>
    suspend fun emit(event: Event)
}