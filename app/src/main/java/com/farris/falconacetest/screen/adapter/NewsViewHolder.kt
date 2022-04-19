package com.farris.falconacetest.screen.adapter

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.farris.falconacetest.databinding.ItemNewsBinding
import com.farris.falconacetest.screen.NewsItem
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(private val binding: ItemNewsBinding) : BaseNewsViewHolder(binding.root) {

    companion object {
        private val TimeFormat_1 = SimpleDateFormat("MMM d, yyyy", Locale.US)
        private val TimeFormat_2 = SimpleDateFormat("K:mm aa", Locale.US)
    }

    override fun bind(item: NewsItem) {
        if (item is NewsItem.News) {

            if (item.thumbnail.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(item.thumbnail)
                    .into(binding.iconImageView)
            } else {
                Glide.with(itemView.context)
                    .load(item.thumbnail)
                    .into(binding.iconImageView)
            }

            if (item.created != null) {
                binding.timeTextView.isVisible = true
                val show = "${TimeFormat_1.format(item.created.timeInMillis)} at ${TimeFormat_2.format(item.created.timeInMillis)}"
                binding.timeTextView.text = show
            } else {
                binding.timeTextView.isVisible = false
            }

            if (item.subscript.isNotEmpty()) {
                binding.customTextView.isVisible = true
                binding.customTextView.text = item.subscript
            } else {
                binding.timeTextView.isVisible = false
            }

            if (item.mainTitle.isNotEmpty()) {
                binding.titleTextView.isVisible = true
                binding.titleTextView.text = item.mainTitle
            } else {
                binding.titleTextView.isVisible = false
            }

            if (item.subTitle.isNotEmpty()) {
                binding.subTitleTextView.isVisible = true
                binding.subTitleTextView.text = item.subTitle
            } else {
                binding.subTitleTextView.isVisible = false
            }
        }
    }
}