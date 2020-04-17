package com.example.nikeurbandictionary.di

import com.example.nikeurbandictionary.di.modules.MainViewModelModule
import com.example.nikeurbandictionary.di.modules.ServiceModule
import com.example.nikeurbandictionary.di.modules.ViewModelFactoryModule
import com.example.nikeurbandictionary.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class, ViewModelFactoryModule::class, MainViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}