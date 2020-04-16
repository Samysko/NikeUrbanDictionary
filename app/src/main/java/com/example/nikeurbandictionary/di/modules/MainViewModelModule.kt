package com.example.nikeurbandictionary.di.modules

import androidx.lifecycle.ViewModel
import com.example.nikeurbandictionary.di.ViewModelKey
import com.example.nikeurbandictionary.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMyViewModel(myViewModel: MainViewModel): ViewModel
}