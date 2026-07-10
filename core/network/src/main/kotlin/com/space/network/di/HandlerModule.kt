package com.space.network.di

import com.space.network.exception.ExceptionHandler
import com.space.network.exception.ExceptionHandlerImpl
import com.space.network.handler.ResponseHandler
import com.space.network.handler.ResponseHandlerImpl
import org.koin.dsl.module

val handlerModule = module {
    single<ResponseHandler> { ResponseHandlerImpl(get()) }
    single<ExceptionHandler> { ExceptionHandlerImpl() }
}