package com.ssafy.fundyou

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imgSrc")
    fun ImageView.setImgByResId(resId : Int){
        this.setImageResource(resId)
    }
}