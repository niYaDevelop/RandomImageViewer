package com.example.randomimageviewer.network
import com.example.randomimageviewer.data.RandomImage
import retrofit2.http.GET

interface RandomImageApiService {
    @GET("v1/images/search")
    suspend fun getImage(): List<RandomImage>
}