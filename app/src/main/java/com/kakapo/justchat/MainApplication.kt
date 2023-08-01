package com.kakapo.justchat

import android.app.Application
import com.kakapo.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger(){
        if (BuildConfig.DEBUG){
            Logger.init()
        }
    }
}