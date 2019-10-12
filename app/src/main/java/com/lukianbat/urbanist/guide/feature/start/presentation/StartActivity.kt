package com.lukianbat.urbanist.guide.feature.start.presentation

import android.Manifest
import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import com.lukianbat.urbanist.guide.сore.presentation.activity.EventsActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.lukianbat.urbanist.guide.BR
import com.lukianbat.urbanist.guide.R
import com.lukianbat.urbanist.guide.databinding.ActivityStartBinding
import com.lukianbat.urbanist.guide.feature.list.presentation.PlacesListActivity
import com.lukianbat.urbanist.guide.feature.start.presentation.permission_denied.PermissionDeniedDialog
import javax.inject.Inject

class StartActivity :
    EventsActivity<ActivityStartBinding, StartViewModule, StartViewModule.EventsListener>(),
    StartViewModule.EventsListener {

    @Inject
    override lateinit var viewModel: StartViewModule

    override val eventsListener: StartViewModule.EventsListener = this

    override val layoutId: Int = R.layout.activity_start
    override val viewModelVariableId: Int = BR.viewModel


    override fun showMessage(messageId: Int) {
        Snackbar.make(
            findViewById(android.R.id.content),
            resources.getString(messageId),
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun routeToPlaceList() {
        requestPermissions()
    }


    private fun requestPermissions() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.deniedPermissionResponses.isEmpty().not()) {
                        showDeniedLocationDialog()
                    } else {
                        val intent = Intent(applicationContext, PlacesListActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                showDeniedLocationDialog()
            }.check()
    }

    private fun showDeniedLocationDialog() {
        PermissionDeniedDialog(
            this@StartActivity
        ).show()
    }
}
