package com.space.network.di

import com.space.network.network_observer.NetworkObserver
import com.space.network.network_observer.NetworkObserverImpl
import org.koin.dsl.module

val connectivityModule = module {
    single<NetworkObserver> { NetworkObserverImpl(get()) }
}