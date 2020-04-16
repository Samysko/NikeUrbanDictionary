package com.example.nikeurbandictionary
import android.app.Application
import com.example.nikeurbandictionary.di.AppComponent
import com.example.nikeurbandictionary.di.DaggerAppComponent
import com.example.nikeurbandictionary.di.modules.ServiceModule

class NikeDictionaryApp : Application() {

    val appComponent by lazy { appComponent() }

    private fun appComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .serviceModule(ServiceModule())
            .build()
    }
}