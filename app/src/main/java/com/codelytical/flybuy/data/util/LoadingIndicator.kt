package com.codelytical.flybuy.data.util

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.codelytical.flybuy.R


class LoadingIndicator(private val mActivity: Activity) {
	private lateinit var isdialog: AlertDialog
	fun startLoading(){
		/**set View*/
		val inflater = mActivity.layoutInflater
		val dialogView = inflater.inflate(R.layout.progress_bar,null)
		/**set Dialog*/
		val builder = AlertDialog.Builder(mActivity)
		builder.setView(dialogView)
		builder.setCancelable(false)
		isdialog = builder.create()
		isdialog.show()
	}
	fun isDismiss(){
		isdialog.dismiss()
	}
}