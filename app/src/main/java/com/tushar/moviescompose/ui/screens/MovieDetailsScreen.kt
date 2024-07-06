package com.tushar.moviescompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.tushar.moviescompose.viewmodels.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    id: String
) {
    val vm: MovieDetailsViewModel = hiltViewModel(
        creationCallback = { factory: MovieDetailsViewModel.MovieDetailsFactory ->
            factory.create(id)
        }
    )

}