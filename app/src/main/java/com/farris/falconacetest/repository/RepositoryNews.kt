package com.farris.falconacetest.repository

import java.util.*

data class RepositoryNews(
    val type: String,
    val title: String,
    val source: String,
    val ref: String,
    val style: String,
    val mainTitle: String,
    val subTitle: String,
    val thumbnail: String,
    val subscript: String,
    val createdTime: Calendar?,
    val description: String,
    val section: String,
    val category: List<String>,
    val insideItems: List<RepositoryNews> = emptyList()
)
