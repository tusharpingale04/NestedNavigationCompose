package com.tushar.moviescompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tushar.moviescompose.ui.navigation.Home
import com.tushar.moviescompose.ui.navigation.MovieDetails

@Composable
fun NavScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Home) {
        composable<Home> {
            HomeScreen { id ->
                navController.navigate(MovieDetails(id))
            }
        }
        composable<MovieDetails> { backStackEntry ->
            val details: MovieDetails = backStackEntry.toRoute()
            MovieDetailsScreen(details.id)
        }
    }

}
