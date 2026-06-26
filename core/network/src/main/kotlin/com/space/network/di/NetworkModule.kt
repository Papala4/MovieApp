package com.space.network.di

import org.koin.dsl.module

val networkModule = module {
    includes(
        retrofitModule,
        handlerModule,
        connectivityModule
    )
}