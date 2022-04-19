package com.farris.falconacetest.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farris.falconacetest.repository.GetNewsRepository
import com.farris.falconacetest.repository.RepositoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val newsRepository: GetNewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            newsRepository().getOrNull()?.let { repository ->
                val items = repository.map {
                    when (it) {
                        is RepositoryItem.Divider -> {
                            NewsItem.Divider(it.categoryName)
                        }
                        is RepositoryItem.News -> {
                            NewsItem.News(
                                it.source,
                                it.ref,
                                it.mainTitle,
                                it.subTitle,
                                it.thumbnail,
                                it.subscript,
                                it.created,
                                it.description,
                                it.category
                            )
                        }
                    }
                }

                _state.update {
                    MainViewState(showItems = items)
                }
            }
        }
    }
}