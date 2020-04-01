package com.example.nikeurbandictionary.service

import com.example.nikeurbandictionary.BuildConfig
import com.example.nikeurbandictionary.model.SearchedWordList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanApi {
    @Headers("X-RapidAPI-Key: ${BuildConfig.KEY_URBAN_DICTIONARY}")
    @GET("/define")
    suspend fun getWordDefinitions(@Query("term") term: String): SearchedWordList

    companion object Factory {
        fun create(): UrbanApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_URBAN_DICTIONARY)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(UrbanApi::class.java)
        }
    }
}