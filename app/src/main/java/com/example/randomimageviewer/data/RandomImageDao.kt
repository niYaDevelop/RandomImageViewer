package com.example.randomimageviewer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RandomImageDao {
    @Query("SELECT * FROM favorites")
    suspend fun getFavoriteList(): MutableList<RandomImage>

    @Insert
    suspend fun insertFavorite(favImage: RandomImage)

    @Query("DELETE FROM favorites WHERE id = :favImageId")
    suspend fun deleteFavorite(favImageId: String)
}