package com.tushar.moviescompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.tushar.moviescompose.state.MoviesState
import com.tushar.moviescompose.viewmodels.MoviesViewModel

@Composable
fun RecentScreen(
    onClick: (id: String) -> Unit
) {
    val vm = hiltViewModel<MoviesViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()

    RecentContent(state = state, onClick = onClick)
}

@Composable
fun RecentContent(
    state: MoviesState,
    onClick: (id: String) -> Unit
) {
    val scrollState = rememberLazyListState()
    val pagingItems = state.uiPagingData.pagingData.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id }
        ) {
            val item = pagingItems[it]
            if (item != null) {
                MovieItem(item = item, onClick = onClick)
            }
        }
    }

}

@Composable
fun MovieItem(item: MoviesViewModel.MovieInfo, onClick: (id: String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(150.dp)
            .clickable {
                onClick(item.id)
            }
    ) {
        Text(text = item.title ?: "")
    }

}
