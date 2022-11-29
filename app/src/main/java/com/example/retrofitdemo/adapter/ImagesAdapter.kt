package com.example.retrofitdemo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load

import com.example.retrofitdemo.databinding.ItemImagesBinding
import com.example.retrofitdemo.models.PicturesModel

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.PicsViewHolder>() {

    inner class PicsViewHolder(val binding: ItemImagesBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<PicturesModel>() {
        override fun areItemsTheSame(oldItem: PicturesModel, newItem: PicturesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PicturesModel, newItem: PicturesModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PicsViewHolder(
        ItemImagesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
    override fun getItemCount() =  differ.currentList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PicsViewHolder, position: Int) {
        val picItem = differ.currentList[position]
        holder.binding.apply {
            ivImage.load(picItem.download_url)
            tvImageId.text = picItem.id
            tvImageSize.text = "${picItem.width} x ${picItem.height}"
            tvImageAuthor.text = picItem.author
        }
    }
}