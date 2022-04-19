package com.farris.falconacetest.usecase

import java.util.*

data class UseCaseNews(
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
)
