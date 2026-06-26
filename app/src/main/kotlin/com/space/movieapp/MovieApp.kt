package com.space.movieapp

import android.app.Application
import com.space.movieapp.di.appModule
import com.space.moviedetails.di.movieDetailsDataModule
import com.space.moviedetails.di.useCaseModule
import com.space.moviedetails.di.vmModule
import com.space.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                appModule,
                networkModule,
                movieDetailsDataModule,
                useCaseModule,
                vmModule
            )
        }
    }
}
