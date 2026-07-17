package com.space.home.di

import com.space.usecase.DiscoverMoviesUseCase
import com.space.usecase.GetGenresUseCase
import com.space.usecase.GetPopularMoviesUseCase
import com.space.usecase.SearchMoviesUseCase
import org.koin.dsl.module

val homeUseCaseModule = module {
    factory { GetPopularMoviesUseCase(get()) }
    factory { DiscoverMoviesUseCase(get()) }
    factory { SearchMoviesUseCase(get()) }
    factory { GetGenresUseCase(get()) }
}
