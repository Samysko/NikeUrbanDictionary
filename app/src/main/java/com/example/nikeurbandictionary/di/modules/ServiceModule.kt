package com.example.nikeurbandictionary.di.modules

import com.example.nikeurbandictionary.BuildConfig
import com.example.nikeurbandictionary.service.UrbanApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build())
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: MoshiConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(moshi)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .baseUrl(BuildConfig.URL_URBAN_DICTIONARY)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): UrbanApi {
        return retrofit.create(UrbanApi::class.java)
    }

}