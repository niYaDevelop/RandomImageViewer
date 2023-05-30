package com.example.randomimageviewer.data

import com.example.randomimageviewer.network.RandomImageApiService


interface RandomImageRepository {
    suspend fun getRandomImage(): MutableList<RandomImage>
    suspend fun loadRandomImageList(size: Int): MutableList<RandomImage>
}

class NetworkRandomImageRepository(
    private val randomImageApiService: RandomImageApiService
) : RandomImageRepository {

    override suspend fun getRandomImage(): MutableList<RandomImage> = randomImageApiService.getImage()

    override suspend fun loadRandomImageList(size: Int): MutableList<RandomImage> {
        var randomList = mutableListOf<RandomImage>()
        for(i in 1..size){
            val l = getRandomImage()
            randomList.addAll(l)
        }
        return randomList
    }
}
