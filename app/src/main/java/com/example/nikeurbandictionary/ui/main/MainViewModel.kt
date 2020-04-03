package com.example.nikeurbandictionary.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nikeurbandictionary.model.SearchedWord
import com.example.nikeurbandictionary.service.ResponseUrbanApi
import com.example.nikeurbandictionary.util.UtilCache
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val searchedWordList = MutableLiveData<List<SearchedWord>>()
    val progress = MutableLiveData<Boolean>()

    fun updateSearchedWordList(term: String, sort: Boolean, isOrdered: Boolean = false) = viewModelScope.launch {
        val cache = UtilCache.getCache(term)
        if (cache != null) {
            searchedWordList.value = cache
            searchedWordList.value = sortList(isOrdered, sort, cache)
        } else {
            try {
                val repository = ResponseUrbanApi(getApplication())
                progress.value = true
                val listSearchedWord = repository.retrieveWordDefinitions(term)
                searchedWordList.value = sortList(isOrdered, sort, listSearchedWord.list)
                UtilCache.addToCache(term, listSearchedWord.list)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        progress.value = false
    }

    private fun sortList(isOrdered: Boolean, sort: Boolean, cache: List<SearchedWord>): List<SearchedWord> = when {
            isOrdered && sort -> cache.sortedByDescending { it.thumbs_up }
            isOrdered -> cache.sortedByDescending { it.thumbs_down }
            else -> cache
    }

}