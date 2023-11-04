package com.example.appmasterpago.utils.ui

import android.app.Activity
import android.app.AlertDialog
import com.example.appmasterpago.R

class LoadingDialog {
    var activity: Activity? = null
    var alertDialog: AlertDialog? = null


    fun StartLoadingDialog(myActivity: Activity?) {
        activity=myActivity
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog?.setCancelable(false)
        alertDialog?.show()
    }

    fun DismissDialog() {
        alertDialog?.dismiss();
    }
}