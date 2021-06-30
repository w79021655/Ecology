package com.sample.ecology

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog


class LoadingDialogUtil {

    companion object {
        var mAlertDialog: AlertDialog? = null

        @JvmStatic
        fun showProgressDialog(context: Context) {
            if (mAlertDialog == null) {
                mAlertDialog = AlertDialog.Builder(context, R.style.CustomProgressDialog).create()
            }

            val loadView: View = LayoutInflater.from(context).inflate(R.layout.loading, null)
            mAlertDialog!!.setView(loadView, 0, 0, 0, 0)
            mAlertDialog!!.setCanceledOnTouchOutside(false)
            mAlertDialog!!.show()
        }

        @JvmStatic
        fun dismiss() {
            if (mAlertDialog != null && mAlertDialog!!.isShowing) {
                mAlertDialog!!.dismiss()
            }
        }

        @JvmStatic
        fun cancel() {
            mAlertDialog = null
        }
    }
}