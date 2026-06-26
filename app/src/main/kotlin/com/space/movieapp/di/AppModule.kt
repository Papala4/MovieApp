package com.space.movieapp.di

import com.space.movieapp.ui.MainActivityViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainActivityViewModel() }
}