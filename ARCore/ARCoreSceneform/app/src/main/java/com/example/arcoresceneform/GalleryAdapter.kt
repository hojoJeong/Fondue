package com.example.arcoresceneform

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.arcoresceneform.databinding.GalleryItemBinding

class GalleryAdapter(val items: List<Int>) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private lateinit var binding: GalleryItemBinding
    private lateinit var holder: ViewHolder
    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = GalleryItemBinding.inflate(LayoutInflater.from(parent.context))
        holder = ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.image.setImageResource(R.drawable.ic_launcher_foreground)
            binding.image.setOnClickListener {
                itemClickListener.onItemClicked()
            }
        }
    }
}

