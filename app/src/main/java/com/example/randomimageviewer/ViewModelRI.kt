package com.example.randomimageviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.randomimageviewer.data.RandomImage
import com.example.randomimageviewer.data.RandomImageRepository
import com.example.randomimageviewer.ui.UIStateRI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelRI(private val randomImageRepository: RandomImageRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UIStateRI())
    val uiState: StateFlow<UIStateRI> = _uiState.asStateFlow()
    private var oldPage = 0

    init{
        viewModelScope.launch {
            try{
                val list = randomImageRepository.loadRandomImageList(10)
                _uiState.update { currentState ->
                    currentState.copy(
                        randomList = list,
                        favList = listOf()
                    )
                }
            }catch (e: Exception){ println(e) }
        }
    }

    fun sendPageSelectedEvent(page: Int){
        if(page > oldPage && page > 5){     // если прокрутка идет вперед, загружаем изображение
            viewModelScope.launch {
                try {
                    _uiState.update { currentState ->
                        currentState.copy(
                            randomList = currentState.randomList.plus(randomImageRepository.getRandomImage())
                        )
                    }
                }catch (e: Exception){ println(e) }
            }
        }
        oldPage = page
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RandomImageApplication)
                val randomImageRepository = application.container.randomImageRepository
                ViewModelRI(randomImageRepository = randomImageRepository)
            }
        }
    }
}