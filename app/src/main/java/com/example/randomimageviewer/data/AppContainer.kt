package com.example.randomimageviewer.data

import com.example.randomimageviewer.network.RandomImageApiService
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json


interface AppContainer {
    val randomImageRepository: RandomImageRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.thecatapi.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: RandomImageApiService by lazy {
        retrofit.create(RandomImageApiService::class.java)
    }

    override val randomImageRepository: RandomImageRepository by lazy {
        NetworkRandomImageRepository(retrofitService)
    }
}
