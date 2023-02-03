package com.ssafy.fundyou.ui.funding_participate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentInvitedFondueBinding
import com.ssafy.fundyou.ui.adapter.diffutil.InvitedFundingModelDiffUtil
import com.ssafy.fundyou.ui.funding_participate.model.InvitedFundingModel

class InvitedFondueItemAdapter : ListAdapter<InvitedFundingModel, InvitedFondueItemAdapter.InvitedFondueItemHolder>(InvitedFundingModelDiffUtil){

    class InvitedFondueItemHolder(private val binding: FragmentInvitedFondueBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : InvitedFundingModel){
            binding.funding = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvitedFondueItemHolder {
        val view = DataBindingUtil.inflate<FragmentInvitedFondueBinding>(LayoutInflater.from(parent.context), R.layout.fragment_invited_fondue, parent, false)
        return InvitedFondueItemHolder(view)
    }

    override fun onBindViewHolder(holder: InvitedFondueItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

}