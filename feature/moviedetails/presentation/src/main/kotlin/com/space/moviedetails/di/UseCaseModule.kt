package com.space.moviedetails.di

import com.space.moviedetails.usecase.GetMovieDetailsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMovieDetailsUseCase(get()) }
}