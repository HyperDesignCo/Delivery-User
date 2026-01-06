package com.example.delivaryUser.common.ui.eventcontroller

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class EventController<Event>() : IEventController<Event> {
    private val _event = Channel<Event>()
    override val event: Flow<Event> = _event.receiveAsFlow()

    override suspend fun emit(event: Event) {
        _event.send(event)
    }
}