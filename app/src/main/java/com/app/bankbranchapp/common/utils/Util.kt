package com.app.bankbranchapp.common.utils

import android.app.ProgressDialog
import android.content.Context
import com.app.bankbranchapp.R


var mProgressDialog: ProgressDialog ?= null


fun Context.showProgressDialog() {
    if (mProgressDialog == null) {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.setMessage(this.getString(R.string.loading))
        mProgressDialog!!.isIndeterminate = true
    }
    mProgressDialog!!.show()
}


fun hideProgressDialog() {
    if (mProgressDialog != null && mProgressDialog!!.isShowing) {
        mProgressDialog!!.dismiss()
    }
}

