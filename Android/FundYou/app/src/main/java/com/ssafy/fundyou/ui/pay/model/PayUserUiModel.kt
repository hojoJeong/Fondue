package com.ssafy.fundyou.ui.pay.model

import com.ssafy.fundyou.domain.model.user.UserInfoDomainModel
import com.ssafy.fundyou.ui.mypage.model.UserInfoModel

data class PayUserUiModel(
    val username : String,
    val userBalance : Int
)

fun UserInfoDomainModel.toPayUserUiModel() = PayUserUiModel(
    username = this.userName,
    userBalance = this.point
)