package com.lukianbat.urbanist.urbanist_guide.feature.start

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.databinding.ActivityStartBinding
import com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.PlaceListActivity
import com.lukianbat.urbanist.urbanist_guide.сore.presentation.BaseActivity
import javax.inject.Inject

class StartActivity : BaseActivity<ActivityStartBinding>() {
    override val layoutId = R.layout.activity_start
    @Inject
    lateinit var viewModel: StartViewModule

    override fun initBinding() {
        requireBinding().viewModel = viewModel
    }

    override fun initViewModel(state: Bundle?) {
        viewModel.onBind()
        viewModel.setEventListener(eventsListener)
    }

    private val eventsListener: StartViewModule.EventsListener =
        object : StartViewModule.EventsListener {
            override fun showMessage(message: String) {
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                    .show()
            }

            override fun routeToList() {
                showMessage("город выбран")
                val intent = Intent(applicationContext, PlaceListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }
}
