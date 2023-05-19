package com.example.randomimageviewer

data class UIStateRI(
    val favList: List<RandomImage> = listOf<RandomImage>(),
    val randomList: List<RandomImage> = listOf<RandomImage>(),
    val openFavImageDialog: Boolean = false,
    val favImageToOpen: RandomImage? = null,
    )