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
    }

    private fun initFavoriteItemObserve(){
        likeItemViewModel.likeItemList.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    Log.d(TAG, "initFavoriteItemList: Favorite Item List Loading...")
                }
                is ViewState.Success -> {
                    initFavoriteItemListAdapter(response.value ?: emptyList())
                }
                is ViewState.Error -> {
                    Log.d(TAG, "initFavoriteItemList: Favorite Item List Loading Error...${response.message}")
                }
            }
        }
    }

    private fun initFavoriteItemListAdapter(itemList: List<LikeItemModel>){
        val itemListAdapter = LikeItemListAdapter()
        itemListAdapter.submitList(itemList)
        with(binding.rvFavorite){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }
    }
}