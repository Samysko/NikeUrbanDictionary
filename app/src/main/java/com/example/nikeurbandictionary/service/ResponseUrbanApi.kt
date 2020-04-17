package com.example.nikeurbandictionary.service

import com.example.nikeurbandictionary.model.SearchedWord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseUrbanApi @Inject constructor(private val dataSource: DataSource ) {

    private suspend fun retrieveWordDefinitions(searchTerm: String): List<SearchedWord> {
        val definitionsResponse = dataSource.loadDefinitions(searchTerm)
        return definitionsResponse.list
    }
}