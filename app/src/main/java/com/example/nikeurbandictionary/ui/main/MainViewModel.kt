package com.example.nikeurbandictionary.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeurbandictionary.model.SearchedWord
import com.example.nikeurbandictionary.service.UrbanApi
import com.example.nikeurbandictionary.util.UtilCache
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: UrbanApi) : ViewModel() {
    val searchedWordList = MutableLiveData<List<SearchedWord>>()
    val progress = MutableLiveData<Boolean>()

    /**
     *  This function updates the SearchedWords List's objects by launching a coroutine.
     *
     *  @param term Word to search with Urban Dictionary
     *  @param sort Indicates which type of sorting is going to be applied
     *  @param isOrdered Indicates whether the list is going to be sorted or not
     */
    fun updateSearchedWordList(term: String, sort: Boolean, isOrdered: Boolean = false) = viewModelScope.launch {
        val cache = UtilCache.getCache(term)
        if (cache != null) {
            searchedWordList.value = cache
            searchedWordList.value = sortList(isOrdered, sort, cache)
        } else {
            try {
                progress.value = true
                val listSearchedWord = repository.getWordDefinitions(term)
                searchedWordList.value = sortList(isOrdered, sort, listSearchedWord.list)
                UtilCache.addToCache(term, listSearchedWord.list)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        progress.value = false
    }

    private fun sortList(isOrdered: Boolean, sort: Boolean, searchedWordList: List<SearchedWord>): List<SearchedWord> = when {
        isOrdered && sort -> searchedWordList.sortedByDescending { it.thumbs_up }
        isOrdered -> searchedWordList.sortedByDescending { it.thumbs_down }
        else -> searchedWordList
    }

}