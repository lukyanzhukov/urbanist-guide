package com.lukianbat.urbanist.guide.сore.presentation.eventsdispatcher

interface EventsDispatcherOwner<T> {
    val eventsDispatcher: EventsDispatcher<T>
}