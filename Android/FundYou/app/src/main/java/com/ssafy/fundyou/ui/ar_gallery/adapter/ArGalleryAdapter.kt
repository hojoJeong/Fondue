package com.ssafy.fundyou.ui.ar_gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemArGalleryListBinding
import com.ssafy.fundyou.ui.ar_gallery.model.ArImageUIModel

class ArGalleryAdapter : ListAdapter<ArImageUIModel, ArGalleryAdapter.ViewHolder>(ItemUrlDiffUtil) {
    private lateinit var itemClickListener: ItemClickListener
    private lateinit var itemDownloadEvent : (String, ItemArGalleryListBinding) -> Unit

    interface ItemClickListener {
        fun onItemClicked(addMode: Boolean, url: String)
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
        holder.bind(currentList[position].url)
    }

    inner class ViewHolder(private val binding: ItemArGalleryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.image.apply {
                if (layoutPosition == 0) {
                        setImageResource(R.drawable.ic_outline_add_a_photo_24)
                } else {
                    itemDownloadEvent.invoke(item, binding)
                }
                setOnClickListener {
                    itemClickListener.onItemClicked(layoutPosition == 0, item)
                }
            }
        }
    }

    object ItemUrlDiffUtil : DiffUtil.ItemCallback<ArImageUIModel>(){
        override fun areItemsTheSame(oldItem: ArImageUIModel, newItem: ArImageUIModel): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ArImageUIModel, newItem: ArImageUIModel): Boolean {
            return oldItem == newItem
        }
    }
}

