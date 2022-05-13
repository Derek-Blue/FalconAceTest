package com.farris.falconacetest.screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.farris.falconacetest.R
import com.farris.falconacetest.databinding.ActivityMainBinding
import com.farris.falconacetest.screen.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelAssistedFactory: MainViewModel.Companion.MainViewModelAssistedFactory

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.provideFactory(viewModelAssistedFactory, 8888)
    }

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }

        observerViewState()
    }

    private fun observerViewState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state
                        .map { it.showItems }
                        .distinctUntilChanged()
                        .collectLatest { items ->
                            if (items.isNotEmpty()) {
                                newsAdapter.submitList(items)
                            }
                        }
                }

                launch {
                    viewModel.state
                        .map { it.isLoading }
                        .distinctUntilChanged()
                        .collectLatest { isLoading ->
                            binding.progressBar.isVisible = isLoading
                        }
                }

            }
        }
    }
}