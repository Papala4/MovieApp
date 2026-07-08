package com.space.favorite.di

import com.space.favorite.mapper.FavouriteMovieUiMapper
import com.space.favorite.vm.FavoriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favoriteVmModule = module {
    factory { FavouriteMovieUiMapper() }
    viewModel { FavoriteViewModel(get(), get(), get(), get()) }
}