package com.farris.falconacetest.repository

import java.util.*

sealed class RepositoryItem {

    data class Divider(
        val categoryName: String
    ): RepositoryItem()

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
    ): RepositoryItem()
}
