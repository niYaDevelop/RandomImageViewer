package com.example.randomimageviewer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.randomimageviewer.ViewModelRI
import com.example.randomimageviewer.ui.screens.FavouriteScreen
import com.example.randomimageviewer.ui.screens.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: ViewModelRI, modifier : Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Search.screen_route, modifier = modifier) {
        composable(BottomNavItem.Search.screen_route) {
            SearchScreen(viewModel)
        }
        composable(BottomNavItem.Favourites.screen_route) {
            FavouriteScreen(viewModel)
        }
    }
}


