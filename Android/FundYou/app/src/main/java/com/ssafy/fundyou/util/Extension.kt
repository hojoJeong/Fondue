package com.ssafy.fundyou.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date

fun Context.showToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.showSnackBar(message : String){
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun addComma(number: Int): String = if (number >= 0) {
    "%,d".format(number)
} else {
    "- "
}

fun getFormattedCurrentTime(): String{
    val formatter = SimpleDateFormat("yyyyMMdd_hhmmss_")
    return formatter.format(Date(System.currentTimeMillis()))
}