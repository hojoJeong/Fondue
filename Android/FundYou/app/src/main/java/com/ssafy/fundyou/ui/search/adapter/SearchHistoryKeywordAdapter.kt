package com.ssafy.fundyou.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemRecentKeywordListBinding

class SearchHistoryKeywordAdapter() :
    RecyclerView.Adapter<SearchHistoryKeywordAdapter.SearchHistoryKeywordViewHolder>() {

    private val keywordList = mutableListOf<String>()
    private lateinit var deleteEvent: (List<String>, Int) -> Unit
    private lateinit var clickEvent: (String) -> Unit

    inner class SearchHistoryKeywordViewHolder(private val binding: ItemRecentKeywordListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: String) {
            binding.keyword = keyword
            binding.ivDelete.setOnClickListener {
                deleteEvent.invoke(keywordList, adapterPosition)
            }
            binding.root.setOnClickListener {
                clickEvent.invoke(keyword)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchHistoryKeywordViewHolder = SearchHistoryKeywordViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_recent_keyword_list, parent, false
        )
    )

    override fun onBindViewHolder(holder: SearchHistoryKeywordViewHolder, position: Int) {
        holder.bind(keywordList[position])
    }

    override fun getItemCount() = keywordList.size

    fun refreshAdapter(){
        notifyDataSetChanged()
    }

    fun addKeywordList(list : List<String>){
        keywordList.clear()
        keywordList.addAll(list)
    }

    fun addItemDeleteEvent(event :(String) -> Unit){
        clickEvent = event
    }

    fun addItemClickEvent(event: (List<String>, Int) -> Unit) {
        deleteEvent = event
    }
}