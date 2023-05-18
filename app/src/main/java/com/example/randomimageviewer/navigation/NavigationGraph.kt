package com.example.randomimageviewer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.randomimageviewer.ui.screens.FavouriteScreen
import com.example.randomimageviewer.ui.screens.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier : Modifier) {
    NavHost(navController, startDestination = BottomNavItem.Search.screen_route, modifier = modifier) {
        composable(BottomNavItem.Search.screen_route) {
            SearchScreen()
        }
        composable(BottomNavItem.Favourites.screen_route) {
            FavouriteScreen()
        }
    }
}


