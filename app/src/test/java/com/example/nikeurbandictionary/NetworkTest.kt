package com.example.nikeurbandictionary

import com.example.nikeurbandictionary.service.ResponseUrbanApi
import com.example.nikeurbandictionary.service.UrbanApi
import com.example.nikeurbandictionary.util.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NetworkTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun shouldFetchDataFromRestServer() = runBlockingTest{
        // Given
        val term = "hey"

        // When
        val responseList = UrbanApi.create().getWordDefinitions(term).list

        // Then
        Assert.assertTrue(responseList.isNotEmpty())
    }
}