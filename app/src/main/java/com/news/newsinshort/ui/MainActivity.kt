package com.news.newsinshort.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.news.newsinshort.ui.navigation.AppNavigationGraph
import com.news.newsinshort.ui.theme.NewsInShortTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsInShortTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsInShortEntryPoint()
                }
            }
        }
    }
}

@Composable
fun NewsInShortEntryPoint(){
    AppNavigationGraph()
}

@Preview(showBackground = true)
@Composable
fun NewsInShortEntryPointPReview() {
    NewsInShortTheme {
        NewsInShortEntryPoint()
    }
}