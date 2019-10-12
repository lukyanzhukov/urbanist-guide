package com.lukianbat.urbanist.guide.сore.presentation.eventsdispatcher

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

class EventsDispatcher<ListenerType> : LifecycleObserver {

    private var eventsListener: ListenerType? = null

    private var boundListener: ListenerType? = null

    private val blocks = LinkedList<ListenerType.() -> Unit>()

    fun bind(lifecycleOwner: LifecycleOwner, listener: ListenerType) {
        eventsListener = listener

        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {
        boundListener = eventsListener

        val listener = boundListener ?: return

        blocks.forEach {
            it(listener)
        }

        blocks.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener() {
        boundListener = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clear(source: LifecycleOwner) {
        eventsListener = null
        boundListener = null
        source.lifecycle.removeObserver(this)
    }

    fun dispatchEvent(block: ListenerType.() -> Unit) {
        boundListener?.also(block) ?: blocks.add(block)
    }
}