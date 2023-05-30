package com.example.randomimageviewer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RandomImage::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun randomImageDao(): RandomImageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "favorites")
                    .createFromAsset("database/favorites.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}