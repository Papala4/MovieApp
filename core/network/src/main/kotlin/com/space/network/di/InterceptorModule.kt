package com.space.network.di

import com.space.network.interceptor.AuthInterceptor
import org.koin.dsl.module

val interceptorModule = module {
    single { AuthInterceptor() }
}