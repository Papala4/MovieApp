package com.space.favorite.di

import com.space.favorite.vm.FavoriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favoriteVmModule = module {
    viewModel { FavoriteViewModel(get(), get(), get()) }
}
