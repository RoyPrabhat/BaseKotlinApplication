package com.example.wellthydemoapp.base

import android.app.Application
import com.example.wellthydemoapp.di.ApplicationComponent
import com.example.wellthydemoapp.di.ApplicationModule
import com.example.wellthydemoapp.di.DaggerApplicationComponent

class MyApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}