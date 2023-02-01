package com.ssafy.fundyou

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ssafy.fundyou.util.addComma

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("srcByResId")
    fun ImageView.setImgByResId(resId: Int) {
        this.setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("srcByDrawable")
    fun ImageView.bindImageFromRes(drawable: Drawable) {
        this.setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter("addCommaText")
    fun TextView.addCommaText(num: Int) {
        try {
            this.text = addComma(num)
        } catch (e: NumberFormatException) {
            this.text = "error"
        }
    }

    @JvmStatic
    @BindingAdapter("arVisibility")
    fun TextView.setVisibilityByAr(isAr : Boolean){
        if(isAr) {
            this.visibility = View.VISIBLE
            this.text = "AR"
        }
        else this.visibility = View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("favoriteImage")
    fun ImageView.checkFavoriteFragment(isFavorite : Boolean){
        if(isFavorite) {
            this.setImageResource(R.drawable.ic_favorite)
        } else {
            this.setImageResource(R.drawable.ic_favorite_line)
        }
    }

    @JvmStatic
    @BindingAdapter("favoriteVisibility")
    fun ImageView.setFavoriteVisibility(type : String){
        if(type == "ITEM_DETAIL") this.visibility = View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("participate")
    fun TextView.setFundingParticipate(participate : Int){
        this.text = "${participate}명 참여"
    }

    @JvmStatic
    @BindingAdapter("percentage")
    fun TextView.setPercentage(percent : Int){
        this.text = "${percent}%"
    }

    @JvmStatic
    @BindingAdapter("fundingStateType")
    fun AppCompatButton.setFundingButtonType(state : Boolean){
        if(state){
            this.text = "펀딩 완료"
            this.setBackgroundResource(R.drawable.bg_rect_transparent_midnight_express_radius8_stroke0)
        } else{
            this.text = "펀딩 중단"
            this.setBackgroundResource(R.drawable.bg_transparent_franch_rose_radius8_stroke0)
        }
    }
}