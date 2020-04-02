package com.example.nikeurbandictionary

import com.example.nikeurbandictionary.service.UrbanApi
import com.example.nikeurbandictionary.util.MainCoroutineRule
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NetworkTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun shouldFetchDataFromRestServer() = runBlocking {
            // Given
            val term = "hey"

            // When
            val responseList = UrbanApi.create().getWordDefinitions(term).list

            // Then
            Assert.assertTrue(responseList.isNotEmpty())
    }
}