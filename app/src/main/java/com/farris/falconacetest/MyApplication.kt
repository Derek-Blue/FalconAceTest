package com.farris.falconacetest

import android.app.Application
import com.farris.falconacetest.di.appModule
import com.farris.falconacetest.di.modelModule
import com.farris.falconacetest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    appModule,
                    modelModule,
                    viewModelModule
                )
            )
        }
    }
}