package com.example.randomimageviewer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class RandomImage(val linkURL: String, var initialLiked: Boolean = false){
    var isLiked: Boolean by mutableStateOf(initialLiked)
}
