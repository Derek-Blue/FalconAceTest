package com.farris.falconacetest.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.farris.falconacetest.usecase.GetNewsUseCase
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel @AssistedInject constructor(
    val newsUseCase: GetNewsUseCase,
    @Assisted initId: Int
) : ViewModel() {

    companion object {

        @AssistedFactory
        interface MainViewModelAssistedFactory {
            fun create(initId: Int): MainViewModel
        }

        fun provideFactory(
            assistedFactory: MainViewModelAssistedFactory,
            initId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(initId) as T
            }
        }
    }

    private val _state = MutableStateFlow(MainViewState())
    val state = _state.asStateFlow()

    init {
        Log.d("TAG_ID", "$initId")

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