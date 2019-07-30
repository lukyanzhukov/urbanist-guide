package com.lukianbat.urbanist.urbanist_guide.feature.start

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.lukianbat.urbanist.urbanist_guide.R
import com.lukianbat.urbanist.urbanist_guide.databinding.ActivityStartBinding
import com.lukianbat.urbanist.urbanist_guide.feature.list.presentation.PlaceListActivity
import com.lukianbat.urbanist.urbanist_guide.feature.start.permission_denied.PermissionDeniedDialog
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
        requestPermissions()
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
                finish()
            }

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
        PermissionDeniedDialog(this@StartActivity).show()
    }
}
