package com.ssafy.fundyou.ui.item_detail

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import com.kakao.sdk.template.model.Button
import com.kakao.sdk.template.model.Content
import com.kakao.sdk.template.model.FeedTemplate
import com.kakao.sdk.template.model.Link
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentItemDetailBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailImgAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailDescriptionInfoAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailRelatedAdapter
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailDescriptionModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : BaseFragment<FragmentItemDetailBinding>(R.layout.fragment_item_detail) {

    private val relatedAdapter = ItemDetailRelatedAdapter()
    private var itemImgFullState = false
    private var itemInfoImgSize = 0
    private val itemArgument by navArgs<ItemDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        // 상품 전체 정보 넣기
        binding.productInfo = null
        itemInfoImgSize = binding.ivItemInfo.layoutParams.height

        initItemImgAdapter()
        initItemDetailAdapter()
        /** 아이템 랜덤 상품 */
        //initRelatedItemAdapter()
        initMoreItemInfoButtonEvent()
        addKakaoShareButtonEvent()
    }

    override fun initViewModels() {

    }

    private fun initMoreItemInfoButtonEvent() {
        binding.tvMoreItemInfoImg.setOnClickListener {
            if (itemImgFullState) setItemInfoImgFixSize()
            else setItemInfoImgWrapContent()

            itemImgFullState = !itemImgFullState
        }
    }

    private fun addKakaoShareButtonEvent() {
        binding.btnItemShare.setOnClickListener {
            val feed = makeFeed(
                "테스트1",
                "테스트2",
                "https://static.skmagic.com/image/goods/G000063300/G000063300_7_480x480.jpg",
                1
            )
            sendKakaoLink(feed)
        }
    }

    private fun setItemInfoImgWrapContent() {

        binding.ivItemInfo.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            endToEnd = ConstraintSet.PARENT_ID
            startToStart = ConstraintSet.PARENT_ID
            topToBottom = R.id.div_item_info_img_base
        }

        binding.tvMoreItemInfoImg.text = "상품설명 최소화"
    }

    private fun setItemInfoImgFixSize() {
        val layoutParams = binding.ivItemInfo.layoutParams
        layoutParams.height = itemInfoImgSize
        binding.ivItemInfo.layoutParams = layoutParams

        binding.tvMoreItemInfoImg.text = "상품설명 더보기"
    }

    private fun initItemDetailAdapter() {
        // 상품 상세정보 임시 리스트
        val tempList = listOf(
            ItemDetailDescriptionModel("type1", "value1"),
            ItemDetailDescriptionModel( "type2", "value2")
        )
        val itemDetailAdapter = ItemDetailDescriptionInfoAdapter()

        binding.rvItemInfo.adapter = itemDetailAdapter

        itemDetailAdapter.submitList(tempList)
    }

    private fun initItemImgAdapter() {
        val itemAdapter = ItemDetailImgAdapter()
        // 상품 이미지 임시 리스트
        val itemImgList = listOf("test1","Test2")
        itemAdapter.addItemImgList(itemImgList)

        binding.tvItemImgPage.text = "1 / ${itemImgList.size}"

        with(binding.vpItemImg) {
            adapter = itemAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvItemImgPage.text =
                        "${(position % itemImgList.size) + 1} / ${itemImgList.size}"
                }
            })
        }
    }

    private fun sendKakaoLink(feed: FeedTemplate) {
        if (ShareClient.instance.isKakaoTalkSharingAvailable(requireContext())) {
            // 카카오톡으로 카카오톡 공유 가능
            ShareClient.instance.shareDefault(
                requireContext(), feed
            ) { sharingResult, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡 공유 실패", error)
                } else if (sharingResult != null) {
                    Log.d(TAG, "카카오톡 공유 성공 ${sharingResult.intent}")
                    startActivity(sharingResult.intent)

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
                KakaoCustomTabsClient.openWithDefault(requireContext(), sharerUrl)
            } catch (e: UnsupportedOperationException) {
                // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
            }

            // 2. CustomTabsServiceConnection 미지원 브라우저 열기
            // ex) 다음, 네이버 등
            try {
                KakaoCustomTabsClient.open(requireContext(), sharerUrl)
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
    private fun makeFeed(title: String, description: String, imageUrl: String, itemId: Int) =
        FeedTemplate(
            content = Content(
                title = title, description = description, imageUrl = imageUrl,
                link = Link(mobileWebUrl = "https://play.google.com/store/apps/details?id=com.ssafy.funding")
            ),
            buttons = listOf(
                Button(
                    title = "상품 확인해보기", link = Link(
                        androidExecutionParams = mapOf(
                            "item_id" to itemId.toString(),
                        )
                    )
                )
            )
        )

    companion object {
        private const val TAG = "ItemDetailFragment...."
    }
}