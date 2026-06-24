package com.space.network.di

import org.koin.dsl.module

val networkModule = module {
    includes(
        interceptorModule,
        retrofitModule,
        handlerModule,
        connectivityModule
    )
}