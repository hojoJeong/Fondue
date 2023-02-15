package com.ssafy.fundyou.ui.item_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentItemDetailBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.common.KakaoMessageTool
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailDescriptionInfoAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailImgAdapter
import com.ssafy.fundyou.ui.item_detail.adapter.ItemDetailRelatedAdapter
import com.ssafy.fundyou.ui.item_detail.model.ItemDetailModel
import com.ssafy.fundyou.ui.like.LikeItemViewModel
import com.ssafy.fundyou.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : BaseFragment<FragmentItemDetailBinding>(R.layout.fragment_item_detail) {
    private val likeItemViewModel by activityViewModels<LikeItemViewModel>()
    private val relatedAdapter = ItemDetailRelatedAdapter().apply {
        addItemClickListener { itemId ->
            navigate(ItemDetailFragmentDirections.actionItemDetailFragmentSelf2(itemId))
        }
    }
    private lateinit var itemDetailInfo: ItemDetailModel
    private val itemDetailViewModel by viewModels<ItemDetailViewModel>()
    private val itemArgument by navArgs<ItemDetailFragmentArgs>()
    private lateinit var dialog: ItemAddBottomSheetFragment

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

    private fun addLikeItemClickListener() {
        var likeStatus = itemDetailViewModel.itemDetailInfo.value?.value?.isFavorite
        Log.d(TAG, "addLikeItemClickListener: $likeStatus")
        binding.btnItemLike.setOnClickListener {
            likeItemViewModel.addListItem(itemArgument.itemId)
            likeStatus = when(likeStatus){
                true -> {
                    binding.btnItemLike.setImageResource(R.drawable.ic_favorite_line)
                    false
                }
                else -> {
                    binding.btnItemLike.setImageResource(R.drawable.ic_favorite)
                    true
                }
            }
            Log.d(TAG, "addLikeItemClickListener 좋아요 누른 후 : $likeStatus")
        }
    }

    private fun initResultAddLikeItemObserve() {
        likeItemViewModel.resultModifyListItem.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initResultAddLikeItemObserve: Add Like Item Loading...")
                }
                is ViewState.Success -> {
                    itemDetailViewModel.getItemDetailInfo(itemArgument.itemId)
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "initResultAddLikeItemObserve: Add like Item Error...${response.message}"
                    )
                }
            }
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
                    addLikeItemClickListener()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initViewModels: Error...")
                }
            }
        }
    }

    private fun addKakaoShareButtonEvent() {
        val kakaoMessageTool = KakaoMessageTool(requireContext())
        binding.btnItemShare.setOnClickListener {
            val feed = kakaoMessageTool.makeFeed(
                itemDetailInfo.brand,
                itemDetailInfo.title,
                itemDetailInfo.imgList[0],
                "상품 확인해보기",
                "item_id",
                itemDetailInfo.id
            )
            kakaoMessageTool.sendKakaoLink(feed)
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

    companion object {
        private const val TAG = "ItemDetailFragment...."
    }
}