package com.farris.falconacetest.screen.adapter

import com.farris.falconacetest.databinding.ItemNewsDividerBinding
import com.farris.falconacetest.screen.NewsItem

class DividerViewHolder(private val binding: ItemNewsDividerBinding) :
    BaseNewsViewHolder(binding.root) {

    override fun bind(item: NewsItem) {
        if (item is NewsItem.Divider) {
            binding.titleTextView.text = item.categoryName
        }
    }
}