package com.ssafy.fundyou.ui.favorite

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.fundyou.R
import com.ssafy.fundyou.common.ViewState
import com.ssafy.fundyou.databinding.FragmentFavoriteBinding
import com.ssafy.fundyou.ui.base.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }
    override fun initView() {
        favoriteViewModel.getFavoriteItemList()
    }

    override fun initViewModels() {
        initFavoriteItemObserve()
    }

    private fun initFavoriteItemObserve(){
        favoriteViewModel.favoriteItemList.observe(viewLifecycleOwner){ response ->
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

    private fun initFavoriteItemListAdapter(itemList: List<FavoriteModel>){
        val itemListAdapter = FavoriteItemListAdapter()
        itemListAdapter.submitList(itemList)
        with(binding.rvFavorite){
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = itemListAdapter
        }
    }

}