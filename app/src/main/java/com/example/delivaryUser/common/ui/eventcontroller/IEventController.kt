package com.example.delivaryUser.common.ui.eventcontroller

import kotlinx.coroutines.flow.Flow

interface IEventController<Event> {
    val event: Flow<Event>
    suspend fun emit(event: Event)
}