package com.example.randomimageviewer.ui

import com.example.randomimageviewer.data.RandomImage

data class UIStateRI(
    val favList: MutableList<RandomImage> = mutableListOf<RandomImage>(),
    val randomList: MutableList<RandomImage> = mutableListOf<RandomImage>(),
    val openFavImageDialog: Boolean = false,
    val favImageToOpen: RandomImage? = null,
    )