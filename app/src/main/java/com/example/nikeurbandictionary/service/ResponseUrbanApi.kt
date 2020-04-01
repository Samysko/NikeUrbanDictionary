package com.example.nikeurbandictionary.service

class ResponseUrbanApi {
    private val urbanApi: UrbanApi = UrbanApi.create()

    suspend fun retrieveWordDefinitions(term: String) = urbanApi.getWordDefinitions(term)
}