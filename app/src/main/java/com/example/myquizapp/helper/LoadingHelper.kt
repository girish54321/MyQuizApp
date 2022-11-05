package com.example.myquizapp.helper

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import com.example.myquizapp.R

object LoadingScreen {
    var dialog: ProgressDialog? = null //obj
    fun displayLoadingWithText(context: Context?, text: String?, cancelable: Boolean) { // function -- context(parent (reference))
        dialog = ProgressDialog(context!!)
        dialog!!.setTitle("Kotlin Progress Bar")
        dialog!!.setMessage("Application is loading, please wait")
//        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog!!.setContentView(R.layout.layout_loading_screen)
//        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(cancelable)
//        val textView = dialog!!.findViewById<TextView>(R.id.text)
//        textView.text = text
        try {
            dialog!!.show()
        } catch (e: Exception) {
        }

    }

    fun hideLoading() {
        try {
            if (dialog != null) {
                dialog!!.dismiss()
            }
        } catch (e: Exception) {
        }
    }
}