package com.ssafy.fundyou.ui.item_detail

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
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
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentItemDetailBinding
import com.ssafy.fundyou.ui.base.BaseFragment
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailImgAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailDescriptionInfoAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailRelatedAdapter
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailModel
import com.ssafy.fundyou.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : BaseFragment<FragmentItemDetailBinding>(R.layout.fragment_item_detail) {

    private val relatedAdapter = ItemDetailRelatedAdapter().apply {
        addItemClickListener { itemId ->
            navigate(ItemDetailFragmentDirections.actionItemDetailFragmentSelf2(itemId))
        }
    }
    private lateinit var itemDetailInfo: ItemDetailModel

    private val itemDetailViewModel by viewModels<ItemDetailViewModel>()
    private val itemArgument by navArgs<ItemDetailFragmentArgs>()
    private lateinit var dialog : ItemAddBottomSheetFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        // 상품 전체 정보 넣기
        itemDetailViewModel.getItemDetailInfo(itemArgument.itemId)
        itemDetailViewModel.getRelatedItemList()

        /** 아이템 랜덤 상품 */
        addKakaoShareButtonEvent()

        addItemInWishList()
    }

    override fun initViewModels() {
        initItemDetailViewModel()
        initMainViewModel()
        initAddWishListObserve()
    }

    private fun addItemInWishList() {
        binding.btnAddCart.setOnClickListener {
            // 바텀 슬라이드
            dialog = ItemAddBottomSheetFragment { count, itemId ->
                itemDetailViewModel.addWishList(count, itemId)
            }.apply {
                setItemInfo(binding.productInfo?.id?.toInt()!!, binding.productInfo?.price!!)
            }
            dialog.show(childFragmentManager, tag)
        }
    }

    private fun initMainViewModel() {
        itemDetailViewModel.relatedItemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    relatedAdapter.submitList(emptyList())
                    relatedAdapter.submitList(response.value)
                    binding.rvRelatedItemList.adapter = relatedAdapter
                }
                is ViewState.Error -> {

                }
            }
        }
    }

    private fun initAddWishListObserve() {
        itemDetailViewModel.addWishListStatus.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    dialog.dismiss()
                    requireContext().showToast("선물이 추가되었습니다.")
                }
                is ViewState.Error -> {
                    dialog.dismiss()
                    requireContext().showToast("선물을 추가하지 못했습니다.")
                }
            }
        }
    }

    private fun initItemDetailViewModel() {
        itemDetailViewModel.itemDetailInfo.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.lyMain.visibility = View.INVISIBLE
                    Log.d(TAG, "initViewModels: logading...")
                }
                is ViewState.Success -> {
                    binding.lyMain.visibility = View.VISIBLE
                    itemDetailInfo = response.value!!
                    binding.productInfo = itemDetailInfo

                    initItemImgAdapter()
                    initItemDetailAdapter()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initViewModels: Error...")
                }
            }
        }
    }

    private fun addKakaoShareButtonEvent() {
        binding.btnItemShare.setOnClickListener {
            val feed = makeFeed(
                itemDetailInfo.brand,
                itemDetailInfo.title,
                itemDetailInfo.imgList[0],
                1
            )
            sendKakaoLink(feed)
        }
    }

    private fun initItemDetailAdapter() {
        // 상품 상세정보 임시 리스트
        val itemDetailAdapter = ItemDetailDescriptionInfoAdapter()
        binding.rvItemInfo.adapter = itemDetailAdapter
        itemDetailAdapter.submitList(itemDetailInfo.description)
    }

    private fun initItemImgAdapter() {
        val itemAdapter = ItemDetailImgAdapter()
        // 상품 이미지 임시 리스트
        itemAdapter.addItemImgList(itemDetailInfo.imgList)

        val imageListSize = itemDetailInfo.imgList.size

        binding.tvItemImgPage.text = "1 / $imageListSize"

        with(binding.vpItemImg) {
            adapter = itemAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvItemImgPage.text =
                        "${(position % imageListSize) + 1} / $imageListSize"
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