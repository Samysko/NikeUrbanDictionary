package com.example.nikeurbandictionary.service

import android.content.Context
import com.example.nikeurbandictionary.BuildConfig
import com.example.nikeurbandictionary.model.SearchedWordList
import com.example.nikeurbandictionary.util.UtilService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.io.File
import java.util.concurrent.TimeUnit

interface UrbanApi {
    @Headers("X-RapidAPI-Key: ${BuildConfig.KEY_URBAN_DICTIONARY}")
    @GET("/define")
    suspend fun getWordDefinitions(@Query("term") term: String): SearchedWordList

    companion object Factory {
        fun create(context: Context): UrbanApi {
            val cacheSize = 10 * 1024 * 1024 // 10 MB
            val httpCacheDirectory = File(context.cacheDir, "http-cache")
            val cache = Cache(httpCacheDirectory, cacheSize.toLong())

            val interceptor = Interceptor { chain ->
                var request = chain.request()

                val cacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES)
                    .build()

                if (UtilService.hasNetworkConnection(context)!!) {
                    request = request.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .removeHeader("Pragma")
                        .build()
                } else {
                    request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1)
                        .build()
                }

                chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
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