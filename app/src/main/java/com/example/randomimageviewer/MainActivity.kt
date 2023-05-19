package com.example.randomimageviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.randomimageviewer.navigation.BottomNavigationBar
import com.example.randomimageviewer.navigation.NavigationGraph
import com.example.randomimageviewer.ui.theme.RandomImageViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomImageViewerTheme {
                MainScreenView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    val viewModelRI: ViewModelRI = viewModel()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavigationGraph(navController = navController, viewModelRI, Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    RandomImageViewerTheme {
        MainScreenView()
    }
}