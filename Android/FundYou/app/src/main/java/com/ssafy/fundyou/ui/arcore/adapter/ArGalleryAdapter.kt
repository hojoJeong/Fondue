package com.ssafy.fundyou.ui.arcore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ItemArGalleryListBinding

class ArGalleryAdapter(val items: List<Int>) :
    RecyclerView.Adapter<ArGalleryAdapter.ViewHolder>() {
    private lateinit var binding: ItemArGalleryListBinding
    private lateinit var holder: ViewHolder
    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemArGalleryListBinding.inflate(LayoutInflater.from(parent.context))
        holder = ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: ItemArGalleryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.image.setImageResource(R.drawable.ic_launcher_foreground)
            binding.image.setOnClickListener {
                itemClickListener.onItemClicked()
            }
        }
    }
}

