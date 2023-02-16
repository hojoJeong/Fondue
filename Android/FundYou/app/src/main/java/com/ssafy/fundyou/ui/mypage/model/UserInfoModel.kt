package com.ssafy.fundyou.ui.mypage.model

import android.os.Parcelable
import com.ssafy.fundyou.domain.model.user.UserInfoDomainModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoModel(
  val id: Long,
  val loginId: String,
  val userName: String,
  val status: Boolean,
  val profileImg: String,
  val point: Int,
  val email: String
) : Parcelable

fun UserInfoDomainModel.toUiModel() = UserInfoModel(
  id = this.id,
  loginId = this.loginId,
  userName = this.userName,
  status = this.status,
  profileImg = this.profileImg,
  point = this.point,
  email = this.email ?: ""
)