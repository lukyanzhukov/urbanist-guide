package com.lukianbat.urbanist.guide.сore.presentation.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.lukianbat.urbanist.guide.сore.presentation.eventsdispatcher.EventsDispatcherOwner

abstract class EventsActivity<DB : ViewDataBinding, VM, EL> :
    MvvmActivity<DB, VM>() where VM : ViewModel, VM : EventsDispatcherOwner<EL> {

    protected abstract val eventsListener: EL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.eventsDispatcher.bind(this, eventsListener)
    }
}