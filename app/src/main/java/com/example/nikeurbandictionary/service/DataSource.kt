package com.example.nikeurbandictionary.service

import com.example.nikeurbandictionary.model.SearchedWordList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSource @Inject constructor(private val dictionaryService: UrbanApi) {

    suspend fun loadDefinitions(searchTerm: String): SearchedWordList {
        return dictionaryService.getWordDefinitions(searchTerm)
    }
}