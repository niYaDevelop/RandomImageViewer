package com.example.randomimageviewer.data

import com.example.randomimageviewer.network.RandomImageApiService


interface RandomImageRepository {
    suspend fun getRandomImage(): List<RandomImage>
    suspend fun loadRandomImageList(): List<RandomImage>
}

class NetworkRandomImageRepository(
    private val randomImageApiService: RandomImageApiService
) : RandomImageRepository {

    override suspend fun getRandomImage(): List<RandomImage> = randomImageApiService.getImage()

    override suspend fun loadRandomImageList(): List<RandomImage> {
        var randomList = listOf<RandomImage>()
        for(i in 1..5){
            val l = getRandomImage()
            randomList = randomList.plus(l)
        }
        return randomList
    }
}
