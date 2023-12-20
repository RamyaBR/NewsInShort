package com.news.newsinshort.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.newsinshort.data.AppConstants
import com.news.newsinshort.data.entity.NewsResponse
import com.news.newsinshort.ui.repository.NewsRepository
import com.news.utilities.ResourceState
import com.news.utilities.ResourceState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _news : MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(ResourceState.Loading())
    val news : StateFlow<ResourceState<NewsResponse>> =_news

    init {
        getNews(AppConstants.COUNTRY)
    }

    private fun getNews(country: String){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNewsHeadLine(country)
                .collectLatest {newsResponse ->
                    _news.value = newsResponse

                }
        }
    }

    companion object{
        const val TAG = "NewsViewModel"
    }
}