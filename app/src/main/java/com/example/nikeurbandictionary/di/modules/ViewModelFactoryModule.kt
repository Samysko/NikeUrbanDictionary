package com.example.nikeurbandictionary.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.nikeurbandictionary.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}