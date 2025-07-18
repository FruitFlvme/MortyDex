package com.fruitflvme.mortydex.app

import android.app.Application
import com.fruitflvme.mortydex.app.di.initKoin

class MortyDexApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}