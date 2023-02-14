package com.ssafy.fundyou.ui.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.util.Log
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import com.kakao.sdk.template.model.Button
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.Link

class KakaoMessageTool(private val context: Context) {
    fun sendKakaoLink(feed: FeedTemplate) {
        if (ShareClient.instance.isKakaoTalkSharingAvailable(context)) {
            // 카카오톡으로 카카오톡 공유 가능

            ShareClient.instance.shareDefault(
                context, feed
            ) { sharingResult, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡 공유 실패", error)
                } else if (sharingResult != null) {
                    Log.d(TAG, "카카오톡 공유 성공 ${sharingResult.intent}")
                    context.startActivity(sharingResult.intent)

                    // 카카오톡 공유에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                    Log.w(TAG, "Warning Msg: ${sharingResult.warningMsg}")
                    Log.w(TAG, "Argument Msg: ${sharingResult.argumentMsg}")
                }
            }
        } else {
            // 카카오톡 미설치: 웹 공유 사용 권장
            // 웹 공유 예시 코드
            val sharerUrl = WebSharerClient.instance.makeDefaultUrl(feed)

            // CustomTabs으로 웹 브라우저 열기

            // 1. CustomTabsServiceConnection 지원 브라우저 열기
            // ex) Chrome, 삼성 인터넷, FireFox, 웨일 등
            try {
                KakaoCustomTabsClient.openWithDefault(context, sharerUrl)
            } catch (e: UnsupportedOperationException) {
                // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
            }

            // 2. CustomTabsServiceConnection 미지원 브라우저 열기
            // ex) 다음, 네이버 등
            try {
                KakaoCustomTabsClient.open(context, sharerUrl)
            } catch (e: ActivityNotFoundException) {
                // 디바이스에 설치된 인터넷 브라우저가 없을 때 예외처리
            }
        }
    }

    /**
     * 카카오톡 공유하기 Feed 생성
     * @param1: title - Feed에 넣을 제목
     * @param2: description - Feed설명
     * @param3: image - 이미지 URL
     * */
    fun makeFeed(title: String, description: String, imageUrl: String, buttonTitle: String, key: String, value: Long) =
        FeedTemplate(
            content = Content(
                title = title, description = description, imageUrl = imageUrl,
                link = Link(mobileWebUrl = "https://play.google.com/store/apps/details?id=com.ssafy.fundyou")
            ),
            buttons = listOf(
                Button(
                    title = buttonTitle, link = Link(
                        androidExecutionParams = mapOf(
                            key to value.toString()
                        )
                    )
                )
            )
        )

    companion object {
        private const val TAG = "KakaoMessageTool...."
    }
}