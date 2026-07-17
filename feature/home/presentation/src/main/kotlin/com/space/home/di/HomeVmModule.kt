package com.space.home.di

import com.space.home.mapper.MovieUiMapper
import com.space.home.vm.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeVmModule = module {
    factory { MovieUiMapper() }
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }
}
