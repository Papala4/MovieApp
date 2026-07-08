package com.space.movieapp

import android.app.Application
import com.space.data.di.coreDataModule
import com.space.di.homeDataModule
import com.space.domain.di.coreDomainModule
import com.space.favorite.di.favoriteVmModule
import com.space.home.di.homeUseCaseModule
import com.space.home.di.homeVmModule
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
                coreDataModule,
                coreDomainModule,
                movieDetailsDataModule,
                homeDataModule,
                homeUseCaseModule,
                homeVmModule,
                favoriteVmModule,
                useCaseModule,
                vmModule
            )
        }
    }
}
