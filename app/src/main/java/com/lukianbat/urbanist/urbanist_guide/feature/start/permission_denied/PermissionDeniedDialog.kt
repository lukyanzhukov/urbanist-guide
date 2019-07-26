package com.lukianbat.urbanist.urbanist_guide.feature.start.permission_denied


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.lukianbat.urbanist.urbanist_guide.R
import kotlinx.android.synthetic.main.dialog_denied_permission.*

class PermissionDeniedDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_denied_permission)
        button.setOnClickListener {
            dismiss()
        }
    }
}