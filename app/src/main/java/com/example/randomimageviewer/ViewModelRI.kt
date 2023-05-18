package com.example.randomimageviewer

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelRI: ViewModel() {
    private val _uiState = MutableStateFlow(UIStateRI())
    val uiState: StateFlow<UIStateRI> = _uiState.asStateFlow()
    val modelRI = ModelRI()

    var favList: MutableList<RandomImage> = mutableStateListOf<RandomImage>()
    var randomList: MutableList<RandomImage> = mutableStateListOf<RandomImage>()

    init{
        randomList = modelRI.getRandomList().toMutableStateList()
        favList = modelRI.getFavList().toMutableStateList()
    }

    fun likedImage(randomImage: RandomImage, like: Boolean){


        if(randomList.contains(randomImage)){
            val index = randomList.indexOf(randomImage)
            val isLiked = randomList[index].isLiked
            randomList[index].isLiked = !isLiked
            if(!isLiked) favList.add(randomImage)
            else favList.remove(favList.find{it.linkURL == randomImage.linkURL})
        }


    }


    fun openFavImage(randomImage: RandomImage){

    }

    fun closeFavImage(){

    }


}