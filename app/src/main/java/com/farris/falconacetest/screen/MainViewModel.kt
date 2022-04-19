package com.farris.falconacetest.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farris.falconacetest.usecase.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val newsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            newsUseCase().getOrNull()?.let { useCaseMap ->
                val items = useCaseMap.map {
                    listOf(NewsItem.Divider(it.key)) + it.value.map { data ->
                        NewsItem.News(
                            data.source,
                            data.ref,
                            data.mainTitle,
                            data.subTitle,
                            data.thumbnail,
                            data.subscript,
                            data.createdTime,
                            data.description,
                            data.section
                        )
                    }
                }.flatten()

                _state.update {
                    MainViewState(showItems = items, isLoading = false)
                }
            }
        }
    }
}