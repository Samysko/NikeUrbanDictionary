package com.example.nikeurbandictionary

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nikeurbandictionary.util.MainCoroutineRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NetworkTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun shouldFetchDataFromRestServer() = runBlocking {
            // Given
            val term = "hey"

            // When
            val responseList = UrbanApi.create(instrumentationContext).getWordDefinitions(term).list

            // Then
            Assert.assertTrue(responseList.isNotEmpty())
    }
}