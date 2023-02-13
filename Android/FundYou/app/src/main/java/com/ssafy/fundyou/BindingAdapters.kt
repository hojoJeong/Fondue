package com.ssafy.fundyou

import android.content.ContentValues.TAG
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.chip.Chip
import com.ssafy.fundyou.ui.funding_my_list.adapter.MyFundingListImgAdapter
import com.ssafy.fundyou.util.addComma
import com.ssafy.fundyou.util.getColorNoTheme
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

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
    fun ImageView.checkFavoriteFragment(isFavorite: Boolean) {
        if (isFavorite) {
            this.setImageResource(R.drawable.ic_favorite)
        } else {
            this.setImageResource(R.drawable.ic_favorite_line)
        }
    }

    @JvmStatic
    @BindingAdapter("participate")
    fun TextView.setFundingParticipate(participate: Int) {
        this.text = "${participate}명 참여"
    }

    @JvmStatic
    @BindingAdapter("percentage")
    fun TextView.setPercentage(percent: Float) {
        this.text = "${percent}%"
    }

    @JvmStatic
    @BindingAdapter("fundingStateType")
    fun AppCompatButton.setFundingButtonType(state: Boolean) {
        if (state) {
            this.text = "펀딩 완료"
            this.setBackgroundResource(R.drawable.bg_rect_transparent_midnight_express_radius8_stroke0)
        } else {
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
    fun TextView.setFundingSuccess(success: Boolean) {
        if (success) setTextColor(context.getColorNoTheme(R.color.franch_rose))
        else setTextColor(context.getColorNoTheme(R.color.raven))
    }

    @JvmStatic
    @BindingAdapter("checkFundingParticipate")
    fun Chip.setFundingParticipateButtonType(state: Boolean) {
        if (state) {
            this.text = "참여"
            this.setChipBackgroundColorResource(R.color.cornflower_blue)
        } else {
            this.text = "미참여"
            this.setChipBackgroundColorResource(R.color.arapawa)
        }
    }

    @JvmStatic
    @BindingAdapter("checkFundingEnd")
    fun Chip.setFundingEndButtonType(state: Boolean) {
        if (state) {
            this.text = "진행중"
            this.setChipBackgroundColorResource(R.color.heliotrope)
        } else {
            this.text = "종료"
            this.setChipBackgroundColorResource(R.color.nobel)
        }
    }

    @JvmStatic
    @BindingAdapter("setPrice")
    fun TextView.setFundingPrice(price: Int) {
        val decimalFormat = DecimalFormat("#,##0")
        val content = "${decimalFormat.format(price)}원 펀딩"
        val builder = SpannableStringBuilder(content)
        val normalSpan = StyleSpan(Typeface.BOLD)
        builder.setSpan(
            normalSpan,
            0,
            decimalFormat.format(price).length + 1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        this.text = builder
    }

    @JvmStatic
    @BindingAdapter("setArVisibility")
    fun AppCompatButton.setArIconVisibility(isAr: Boolean) {
        this.visibility = if (isAr) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("setFundingItemImage")
    fun ImageView.setFundingItemImage(img: Int) {
        setImageResource(img)
    }

    @JvmStatic
    @BindingAdapter("setBalanceFundingPrice")
    fun TextView.setBalanceFundingPrice(price: Int) {
        val formatContent = DecimalFormat("#,##0").format(price)
        val content = "${formatContent}원 남았어요!"
        val builder = SpannableStringBuilder(content)
        builder.setSpan(
            RelativeSizeSpan(0.8f),
            formatContent.length,
            formatContent.length + 1,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        builder.setSpan(
            RelativeSizeSpan(0.7f),
            content.length - 5,
            content.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        builder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.green)),
            content.length - 5,
            content.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        this.text = builder
    }

    @JvmStatic
    @BindingAdapter("setRemainPoint")
    fun TextView.setRemainPoint(remain: Int) {
        val formatContent = DecimalFormat("#,##0").format(remain)
        val content = "잔액 : ${formatContent}원"
        this.text = content
    }

    @JvmStatic
    @BindingAdapter("setFundingItemPrice")
    fun TextView.setFundingItemPrice(price: Int) {
        val formatContent = DecimalFormat("#,##0").format(price)
        val content = "잔액 : ${formatContent}원"
        this.text = content
    }

    @JvmStatic
    @BindingAdapter("setItemImage")
    fun ImageView.setItemImage(img: String?) {
        Glide.with(context)
            .load(img)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("setFullSizeImage")
    fun ImageView.setFullSizeImage(imgSrc: String?) {
        Glide.with(context)
            .load(imgSrc)
            .override(Target.SIZE_ORIGINAL)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("setFundingSequence")
    fun TextView.setFundingSequence(sequence: Int) {
        text = "${sequence}째 펀딩"
    }

    @JvmStatic
    @BindingAdapter(value = ["startDate", "endDate"])
    fun TextView.setStartToEndDate(start: Long, end: Long) {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
        text = "${simpleDateFormat.format(start)}~${simpleDateFormat.format(end)}"
    }

    @JvmStatic
    @BindingAdapter("styleByStatus")
    fun TextView.setStyleByStatus(status: Boolean) {
        if (status) this.setTextAppearance(R.style.base_font_bold_20_franch_rose)
        else this.setTextAppearance(R.style.base_font_bold_20_matter_horn)
    }

    @JvmStatic
    @BindingAdapter("deadLine")
    fun TextView.setDeadLine(deadLine: Int) {
        this.text = if (deadLine < 0) "마감" else "D-${deadLine}"
    }

    @JvmStatic
    @BindingAdapter("participateTextStyle")
    fun TextView.setParticipateTextStyle(status : Boolean){
        if(status) setTextAppearance(R.style.base_font_medium_12_tomato)
        else setTextAppearance(R.style.base_font_medium_12_matter_horn)
        // includeFontPadding이 적용안됨, 추가로 더 해줘야함
        this.includeFontPadding = false
    }

    @JvmStatic
    @BindingAdapter("itemCount")
    fun TextView.setItemCount(count : Int){
        text = "(${count})"
    }
}