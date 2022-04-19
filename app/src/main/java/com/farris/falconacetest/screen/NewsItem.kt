package com.farris.falconacetest.screen

import java.util.*

sealed class NewsItem {

    data class Divider(
        val categoryName: String
    ) : NewsItem()

    data class News(
        val source: String,
        val ref: String,
        val mainTitle: String,
        val subTitle: String,
        val thumbnail: String,
        val subscript: String,
        val created: Calendar? = null,
        val description: String,
        val category: String
    ) : NewsItem()
}
