package com.tushar.moviescompose.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class MovieDetails(val id: String)

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    data object Recents : NavigationItem("Recents", Icons.Rounded.Home, "Recents")
    data object Favorites : NavigationItem("Favorites", Icons.Rounded.Favorite, "Favorites")
}