package com.example.randomimageviewer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModelRI: ViewModel() {
    private val _uiState = MutableStateFlow(UIStateRI())
    val uiState: StateFlow<UIStateRI> = _uiState.asStateFlow()
    private val modelRI = ModelRI()

    init{
        _uiState.update { currentState ->
            currentState.copy(
                randomList = modelRI.getRandomList(),
                favList = modelRI.getFavList()
            )
        }
    }

    fun likedImage(randomImage: RandomImage){

        val isLiked = !randomImage.isLiked
        randomImage.isLiked = isLiked
        if(isLiked){
            _uiState.update { currentState ->
                currentState.copy(
                    favList = currentState.favList.plus(randomImage)
                )
            }
        }
        else{
            _uiState.update { currentState ->
                currentState.copy(
                    favList = currentState.favList.minus(randomImage)
                )
            }
            if(_uiState.value.favList.isEmpty()) closeFavImage()
        }
    }

    fun openFavImage(randomImage: RandomImage){

        _uiState.update { currentState ->
            currentState.copy(
                favImageToOpen = randomImage,
                openFavImageDialog = true
            )
        }
    }

    fun closeFavImage(){
        _uiState.update { currentState ->
            currentState.copy(
                openFavImageDialog = false
            )
        }
    }
}