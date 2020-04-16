package com.example.nikeurbandictionary.service

import com.example.nikeurbandictionary.BuildConfig
import com.example.nikeurbandictionary.model.SearchedWordList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanApi {
    @Headers("X-RapidAPI-Key: ${BuildConfig.KEY_URBAN_DICTIONARY}")
    @GET("/define")
    suspend fun getWordDefinitions(@Query("term") term: String): SearchedWordList

}