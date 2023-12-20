package com.news.newsinshort.ui.screens

import EmptyyStateComponent
import NewsRowComponent
import ProgressLoader
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.news.newsinshort.ui.viewModel.NewsViewModel
import com.news.utilities.ResourceState

const val TAG = "HomeScreen"
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
){
    val newsRes by newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp,
        pageCount = 100 ) { page ->

            when(newsRes){
                is ResourceState.Loading ->{
                    Log.d(TAG, "Inside_Loading")
                    ProgressLoader()
                }
                is ResourceState.Success ->{
                    val response = (newsRes as ResourceState.Success).data
                    Log.d(TAG, "Inside_Success ${response.status} = ${response.totalResults}")

                    if(response.articles.isNotEmpty()) {
                        NewsRowComponent(page, response.articles[page])
                    }
                    else
                    {
                        EmptyyStateComponent()
                    }
                }

                is ResourceState.Error ->{
                    Log.d(TAG, "Inside_Error")
                }
            }
    }
}

@Preview
@Composable
fun HomeScreenPReview(){
    HomeScreen()
}