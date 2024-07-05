package com.tushar.moviescompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.tushar.moviescompose.viewmodels.MoviesViewModel

@Composable
fun MainScreen(
    modifier: Modifier
) {
    val vm = hiltViewModel<MoviesViewModel>()
    val pagingItems = vm.movies.collectAsLazyPagingItems()

    LazyColumn(modifier = modifier) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id },
        ) { index ->
            val item = pagingItems[index]
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)) {
                Text(text = item?.title ?: "", color = Color.White)
            }
        }
    }
}