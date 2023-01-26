package com.ssafy.fundyou.ui.login.banner.model

import android.os.Parcelable
import dagger.multibindings.IntoMap
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginBannerModel(
    val img: Int,
    val content: String
) : Parcelable