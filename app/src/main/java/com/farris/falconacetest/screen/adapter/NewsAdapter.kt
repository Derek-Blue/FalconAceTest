package com.farris.falconacetest.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.farris.falconacetest.R
import com.farris.falconacetest.databinding.ItemNewsBinding
import com.farris.falconacetest.databinding.ItemNewsDividerBinding
import com.farris.falconacetest.screen.NewsItem

class NewsAdapter : ListAdapter<NewsItem, BaseNewsViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseNewsViewHolder {
        return when (viewType) {
            0 -> {
                DividerViewHolder(
                    ItemNewsDividerBinding.bind(
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.item_news_divider,
                            parent,
                            false
                        )
                    )
                )
            }
            else -> {
                NewsViewHolder(
                    ItemNewsBinding.bind(
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.item_news,
                            parent,
                            false
                        )
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is NewsItem.Divider) {
            0
        } else {
            1
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return if (oldItem is NewsItem.Divider && newItem is NewsItem.Divider) {
                oldItem.categoryName == newItem.categoryName
            } else if (oldItem is NewsItem.News && newItem is NewsItem.News) {
                oldItem.mainTitle == newItem.mainTitle
            } else {
                false
            }
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

    }
}