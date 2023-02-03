package com.ssafy.fundyou.ui.arcore.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemArGalleryListBinding

object ItemUrlDiffUtil : DiffUtil.ItemCallback<String>(){
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class ArGalleryAdapter() : ListAdapter<String, ArGalleryAdapter.ViewHolder>(ItemUrlDiffUtil) {
    private lateinit var itemClickListener: ItemClickListener
    private lateinit var itemDownloadEvent : (String, ItemArGalleryListBinding) -> Unit

    interface ItemClickListener {
        fun onItemClicked()
    }

    fun addItemDownLoadEvent(event : (String, ItemArGalleryListBinding) -> Unit) {
        this.itemDownloadEvent = event
    }

    fun addItemClickListener(listener: ItemClickListener){
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemArGalleryListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemArGalleryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            if(item == "0") {
                binding.image.apply {
                    setImageResource(R.drawable.ic_outline_add_a_photo_24)
                    setOnClickListener {
                        itemClickListener.onItemClicked()
                    }
                }
            }else{
                itemDownloadEvent.invoke(item, binding)
            }
        }
    }
}

