package com.example.randomimageviewer.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomImage(
    val id: String,
    @SerialName(value = "url")
    val linkURL: String,
    var initialLiked: Boolean = false,
    val width: Int? = null,
    val height: Int? = null

){
    var isLiked: Boolean by mutableStateOf(initialLiked)
}
