package com.example.nikeurbandictionary.service

import android.content.Context

class ResponseUrbanApi(context: Context) {
    private val urbanApi: UrbanApi = UrbanApi.create(context)

    suspend fun retrieveWordDefinitions(term: String) = urbanApi.getWordDefinitions(term)
}