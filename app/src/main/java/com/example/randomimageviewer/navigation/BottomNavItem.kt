package com.example.randomimageviewer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title:String, var icon: ImageVector, var screen_route:String){

    object Search : BottomNavItem("Search", Icons.Filled.Search,"home")
    object Favourite: BottomNavItem("Favourite",Icons.Filled.Favorite,"favourite")
}