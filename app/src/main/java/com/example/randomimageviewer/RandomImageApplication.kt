package com.example.randomimageviewer

import android.app.Application
import com.example.randomimageviewer.data.AppContainer
import com.example.randomimageviewer.data.AppDatabase
import com.example.randomimageviewer.data.DefaultAppContainer

class RandomImageApplication : Application(){
    val container: AppContainer by lazy{ DefaultAppContainer() }
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}