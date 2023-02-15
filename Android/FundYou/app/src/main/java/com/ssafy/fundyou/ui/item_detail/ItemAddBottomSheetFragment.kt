package com.ssafy.fundyou.ui.item_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentItemAddBinding
import com.ssafy.fundyou.util.extension.addComma

class ItemAddBottomSheetFragment(private val addItem: (Int, Int) -> Unit) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemAddBinding
    private var itemId = -1
    private var itemPrice = -1
    private var count = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCountEvent()
        addButtonClickEvent()
        binding.price = itemPrice
    }

    private fun addCountEvent() {
        with(binding) {
            ivMinus.setOnClickListener {
                setCountText(false)
            }

            ivPlus.setOnClickListener {
                setCountText(true)
            }
        }
    }

    private fun setCountText(type: Boolean) {
        if (type) {
            binding.tvCount.text = "${++count}"
            binding.ivMinus.isEnabled = true
        } else {
            if (count > 1) {
                binding.tvCount.text = "${--count}"
            } else binding.ivMinus.isEnabled = false
        }
        binding.tvTotalPrice.text = addComma(itemPrice * count)
    }

    private fun addButtonClickEvent(){
        binding.btnAddItem.setOnClickListener {
            addItem.invoke(count, itemId)
        }
    }

    fun setItemInfo(itemId: Int, itemPrice: Int) {
        this.itemId = itemId
        this.itemPrice = itemPrice
    }

}