package com.example.randomimageviewer

import androidx.compose.runtime.mutableStateListOf

data class UIStateRI(
//    var favList: MutableList<RandomImage> = mutableStateListOf<RandomImage>(),
//    var randomList: MutableList<RandomImage> = mutableStateListOf<RandomImage>(),
    var openFavImageDialog: Boolean = false,
    var favImageToOpen: RandomImage? = null,


    )