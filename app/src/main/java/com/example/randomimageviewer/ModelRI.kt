package com.example.randomimageviewer

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter

class ModelRI {
    val imageUrl: String = "https://randomwordgenerator.com/img/picture-generator/57e2d146425aa414f1dc8460962e33791c3ad6e04e50744076287ad3904ecd_640.jpg"
    val anotherImgUrl = "https://randomwordgenerator.com/img/picture-generator/54e9d7464b55a514f1dc8460962e33791c3ad6e04e507441722973d49548c4_640.jpg"

    fun getRandomList(): MutableList<RandomImage>{

        return mutableStateListOf(RandomImage(imageUrl), RandomImage(anotherImgUrl), RandomImage(anotherImgUrl), RandomImage(imageUrl))
    }

    fun getFavList(): MutableList<RandomImage>{

//        return mutableListOf(RandomImage(imageUrl))
        return mutableStateListOf()
    }

}