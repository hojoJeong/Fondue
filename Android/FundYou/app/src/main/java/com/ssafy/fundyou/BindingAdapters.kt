package com.ssafy.fundyou

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ssafy.fundyou.util.extension.addComma
import com.ssafy.fundyou.util.extension.getColorNoTheme
import com.google.android.material.chip.Chip
import java.text.DecimalFormat

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
        this.text = addComma(num)
    }

    @JvmStatic
    @BindingAdapter("arVisibility")
    fun TextView.setVisibilityByAr(isAr: Boolean) {
        if (isAr) {
            this.visibility = View.VISIBLE
            this.text = "AR"
        } else this.visibility = View.INVISIBLE
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

    @JvmStatic
    @BindingAdapter("fundingItems")
    fun TextView.setFundingItemsText(fundingList: List<String>) {
        text = if (fundingList.size > 1) "${fundingList[0]} 외 ${fundingList.size - 1}건"
        else fundingList[0]
    }

    @JvmStatic
    @BindingAdapter("fundingSuccess")
    fun TextView.setFundingSuccess(success : Boolean){
        if(success) setTextColor(context.getColorNoTheme(R.color.franch_rose))
        else setTextColor(context.getColorNoTheme(R.color.raven))
    }

    @JvmStatic
    @BindingAdapter("checkFundingParticipate")
    fun Chip.setFundingParticipateButtonType(state : Boolean){
        if(state){
            this.text = "참여"
            this.setChipBackgroundColorResource(R.color.cornflower_blue)
        } else{
            this.text = "미참여"
            this.setChipBackgroundColorResource(R.color.arapawa)
        }
    }

    @JvmStatic
    @BindingAdapter("checkFundingEnd")
    fun Chip.setFundingEndButtonType(state : Boolean){
        if(state){
            this.text = "진행중"
            this.setChipBackgroundColorResource(R.color.heliotrope)
        } else{
            this.text = "종료"
            this.setChipBackgroundColorResource(R.color.nobel)
        }
    }

    @JvmStatic
    @BindingAdapter("setPrice")
    fun TextView.setFundingPrice(price : Int){
        val decimalFormat = DecimalFormat("#,##0")
        val content = "${decimalFormat.format(price)}원 펀딩"
        val builder = SpannableStringBuilder(content)
        val normalSpan = StyleSpan(Typeface.BOLD)
        builder.setSpan(normalSpan, 0, decimalFormat.format(price).length+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.text = builder
    }

    @JvmStatic
    @BindingAdapter("setArVisibility")
    fun AppCompatButton.setArIconVisibility(isAr : Boolean){
        this.visibility = if(isAr) View.VISIBLE else View.GONE
    }

}