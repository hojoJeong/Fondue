package com.ssafy.fundyou.ui.like

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentFavoriteBinding
import com.ssafy.fundyou.ui.common.BaseFragment

class LikeFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val likeItemViewModel by activityViewModels<LikeItemViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        likeItemViewModel.getLikeItemList()
    }

    override fun initViewModels() {
        initFavoriteItemObserve()
        initResultModifyLikeItemObserve()
    }

    private fun initFavoriteItemObserve() {
        likeItemViewModel.likeItemList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "initFavoriteItemList: Favorite Item List Loading...")
                }
                is ViewState.Success -> {
                    if(response.value?.isEmpty()!!){
                        binding.lyFavoriteNoItem.tvNoKeyword.text = "찜한 상품이 없습니다."
                        binding.lyFavoriteNoItem.root.visibility = View.VISIBLE
                        initFavoriteItemListAdapter(emptyList())
                    } else {
                        binding.lyFavoriteNoItem.root.visibility = View.GONE
                        initFavoriteItemListAdapter(response.value ?: emptyList())
                    }
                }
                is ViewState.Error -> {
                    Log.d(
                        TAG,
                        "initFavoriteItemList: Favorite Item List Loading Error...${response.message}"
                    )
                }
            }
        }
    }

    private fun initResultModifyLikeItemObserve(){
        likeItemViewModel.resultModifyListItem.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d(TAG, "initResultModifyLikeItemObserve: Remove Like Item Loading...")
                }
                is ViewState.Success -> {
                    likeItemViewModel.getLikeItemList()
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initResultModifyLikeItemObserve: Remove Like Item Error...${response.message}")
                }
            }
        }
    }

    private fun initFavoriteItemListAdapter(itemList: List<LikeItemModel>) {
        val itemListAdapter = LikeItemListAdapter()
        with(itemListAdapter) {
            submitList(itemList)
            addItemClickLIstener { id ->
                navigate(LikeFragmentDirections.actionFavoriteFragmentToItemDetailFragment(id))
            }
            addRemoveItemBtnClickListener { id ->
                likeItemViewModel.addListItem(id)
            }
        }
        with(binding.rvFavorite) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }

    }
}