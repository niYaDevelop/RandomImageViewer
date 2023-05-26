package com.example.randomimageviewer.ui

import com.example.randomimageviewer.data.RandomImage

data class UIStateRI(
    val favList: List<RandomImage> = listOf<RandomImage>(),
    val randomList: List<RandomImage> = listOf<RandomImage>(),
    val openFavImageDialog: Boolean = false,
    val favImageToOpen: RandomImage? = null,
    )