package com.farris.falconacetest.screen

data class MainViewState(
    val showItems: List<NewsItem> = emptyList(),
    val isLoading: Boolean = true
)
