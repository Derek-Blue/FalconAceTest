package com.farris.falconacetest.screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.farris.falconacetest.screen.NewsItem

abstract class BaseNewsViewHolder(view:View): RecyclerView.ViewHolder(view) {

    abstract fun bind(item: NewsItem)
}