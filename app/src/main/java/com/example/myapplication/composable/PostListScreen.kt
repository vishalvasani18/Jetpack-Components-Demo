
package com.example.myapplication.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.PostViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun PostListScreen(
    viewModel: PostViewModel = viewModel()
) {
    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()
    val error by viewModel.error.collectAsState()

    val listState = rememberLazyListState()

    // Load the next page when nearing the bottom.
    LaunchedEffect(listState, posts.size) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
            .filterNotNull()
            .distinctUntilChanged()
            .collect { lastVisibleIndex ->
                if (
                    posts.isNotEmpty() &&
                    lastVisibleIndex >= posts.size - 3
                ) {
                    viewModel.loadNextPage()
                }
            }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = posts,
                key = { it.id!! }
            ) { post ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = post.title ?: "")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = post.body ?: "")
                    }
                }
            }

            // Loader at the bottom for pagination.
            if (isLoadingMore) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (error != null && posts.isNotEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = error ?: "Failed to load posts")
                        androidx.compose.material3.Button(
                            onClick = { viewModel.loadNextPage() }
                        ) {
                            Text("Retry")
                        }
                    }
                }
            }
        }

        // Initial loading indicator.
        if (isLoading && posts.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Initial request error.
        if (error != null && posts.isEmpty() && !isLoading) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = error ?: "Failed to load posts")
                androidx.compose.material3.Button(
                    onClick = { viewModel.fetchPosts() }
                ) {
                    Text("Retry")
                }
            }
        }
    }
}
