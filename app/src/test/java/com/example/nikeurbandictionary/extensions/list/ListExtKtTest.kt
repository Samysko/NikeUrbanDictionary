package com.example.nikeurbandictionary.extensions.list

import com.example.nikeurbandictionary.model.SearchedWord
import com.example.nikeurbandictionary.util.THUMBS_DOWN
import com.example.nikeurbandictionary.util.THUMBS_UP
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ListExtKtTest {
    private lateinit var listOfSearchedWord: List<SearchedWord>

    @Before
    fun setUp() {
        val searchedWordWithLikes = SearchedWord("dog", 200, 0, "")
        val searchedWordWithDislikes = SearchedWord("dog", 0, 100, "")
        val searchedWordWithNoLikesAndDislikes = SearchedWord("dog", 0, 0, "")
        listOfSearchedWord = listOf(searchedWordWithDislikes, searchedWordWithLikes, searchedWordWithNoLikesAndDislikes)
    }

    @Test
    fun Should_SortDescendingByLikes_When_SortByAndSortEnabledEqualsToTrue() {
        val input = listOfSearchedWord
        val sortBy = THUMBS_UP
        val sortEnabled = true
        val expected = listOfSearchedWord.sortedByDescending { it.thumbs_up }

        val output: List<SearchedWord> = input.sortByLikes(sortBy, sortEnabled)

        assertEquals(expected, output)

    }

    @Test
    fun Should_SortDescendingByLikes_When_SortByEqualsToFalseAndSortEnabledEqualsToTrue() {
        val input = listOfSearchedWord
        val sortBy = THUMBS_DOWN
        val sortEnabled = true
        val expected = listOfSearchedWord.sortedByDescending { it.thumbs_down }

        val output: List<SearchedWord> = input.sortByLikes(sortBy, sortEnabled)

        assertEquals(expected, output)

    }

    @Test
    fun Should_GetDefaultSort_When_SortByEqualsToTrueAndSortEnabledEqualsToFalse() {
        val input = listOfSearchedWord
        val sortBy = THUMBS_UP
        val sortEnabled = false
        val expected = listOfSearchedWord

        val output: List<SearchedWord> = input.sortByLikes(sortBy, sortEnabled)

        assertEquals(expected, output)
    }

    @Test
    fun Should_GetDefaultSort_When_SortByEqualsToFalseAndSortEnabledEqualsToFalse() {
        val input = listOfSearchedWord
        val sortBy = THUMBS_DOWN
        val sortEnabled = false
        val expected = listOfSearchedWord

        val output: List<SearchedWord> = input.sortByLikes(sortBy, sortEnabled)

        assertEquals(expected, output)
    }
}