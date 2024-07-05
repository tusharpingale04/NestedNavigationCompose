package com.tushar.moviescompose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tushar.moviescompose.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    init {
        getAllFavorites()
    }

    private fun getAllFavorites() {
        viewModelScope.launch (Dispatchers.IO) {

        }
    }

}