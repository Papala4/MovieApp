package com.space.movieapp

import android.app.Application
import com.space.movieapp.di.appModule
import com.space.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(
                networkModule,
                appModule
            )
        }
    }
}