package com.example.randomimageviewer

import android.app.Application
import com.example.randomimageviewer.data.AppContainer
import com.example.randomimageviewer.data.DefaultAppContainer

class RandomImageApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}