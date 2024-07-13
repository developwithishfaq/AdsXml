package com.example.adsxml.base

import android.app.Application
import com.example.adsxml.di.SharedModule
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(SharedModule)
        }

    }

}