package com.ssafy.fundyou

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("srcByResId")
    fun ImageView.setImgByResId(resId : Int){
        this.setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("srcByDrawable")
    fun ImageView.bindImageFromRes(drawable: Drawable) {
        this.setImageDrawable(drawable)
    }
}