package com.space.domain.di

import com.space.domain.usecase.AddFavouriteMovieUseCase
import com.space.domain.usecase.GetFavouriteMoviesUseCase
import com.space.domain.usecase.RemoveFavouriteMovieUseCase
import org.koin.dsl.module

val coreDomainModule = module {
    factory { AddFavouriteMovieUseCase(get()) }
    factory { RemoveFavouriteMovieUseCase(get()) }
    factory { GetFavouriteMoviesUseCase(get()) }
}
