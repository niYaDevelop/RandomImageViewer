package com.example.randomimageviewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.randomimageviewer.data.RandomImage
import com.example.randomimageviewer.data.RandomImageDao
import com.example.randomimageviewer.data.RandomImageRepository
import com.example.randomimageviewer.ui.UIStateRI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelRI(
    private val randomImageRepository: RandomImageRepository,
    private val randomImageDao: RandomImageDao
    ): ViewModel() {

    private val _uiState = MutableStateFlow(UIStateRI())
    val uiState: StateFlow<UIStateRI> = _uiState.asStateFlow()
    private var oldPage = 0

    init{
        viewModelScope.launch {
            try{
                _uiState.update { currentState ->
                    currentState.copy(
                        randomList = randomImageRepository.loadRandomImageList(10).toMutableList(),
                        favList = randomImageDao.getFavoriteList().toMutableList()
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
                            randomList = currentState.randomList.plus(randomImageRepository.getRandomImage()).toMutableList()
                        )
                    }
                }catch (e: Exception){ println(e) }
            }
        }
        oldPage = page
    }

    fun likedImage(randomImage: RandomImage){

        viewModelScope.launch {
            try {
                val isLiked = !randomImage.isLiked
                val newRandomImage = randomImage.copy(isLiked = isLiked)
                val indexRand = _uiState.value.randomList.indexOfFirst { it.id == randomImage.id }
                val indexFav = _uiState.value.favList.indexOfFirst { it.id == randomImage.id }

                val newRandList: MutableList<RandomImage> = _uiState.value.randomList
                if(indexRand > -1) newRandList[indexRand] = newRandomImage
                val newFavList: MutableList<RandomImage> = _uiState.value.favList
                if(indexFav > -1) newFavList[indexFav] = newRandomImage

                if(isLiked){
                    _uiState.update { currentState ->
                        currentState.copy(
                            favList = newFavList.plus(newRandomImage).toMutableList(),
                            randomList = newRandList
                        )
                    }
                    randomImageDao.insertFavorite(newRandomImage)
                }else{
                    _uiState.update { currentState ->
                        currentState.copy(
                            favList = newFavList.minus(newRandomImage).toMutableList(),
                            randomList = newRandList
                        )
                    }
                    randomImageDao.deleteFavorite(newRandomImage.id)
                    if(_uiState.value.favList.isEmpty()) closeFavImage()
                }
            }catch (e: Exception){println(e)}
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
                val randomImageDao = application.database.randomImageDao()
                ViewModelRI(randomImageRepository = randomImageRepository, randomImageDao = randomImageDao)
            }
        }
    }
}