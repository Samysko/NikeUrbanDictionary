package com.example.nikeurbandictionary.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeurbandictionary.extensions.list.sortByLikes
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
     *  @param sortBy Indicates which type of sorting is going to be applied
     *  @param sortingEnabled Indicates whether the list is going to be sorted or not
     */
    fun updateSearchedWordList(term: String, sortBy: Boolean, sortingEnabled: Boolean = false) = viewModelScope.launch {
        val cache = UtilCache.getCache(term)

        if (cache != null) {
            searchedWordList.value = cache
            searchedWordList.postValue(searchedWordList.value?.sortByLikes(sortBy, sortingEnabled))
        } else {
            try {
                progress.value = true
                val listSearchedWord = repository.getWordDefinitions(term)
                searchedWordList.value = listSearchedWord.list
                UtilCache.addToCache(term, listSearchedWord.list)
                searchedWordList.postValue(searchedWordList.value?.sortByLikes(sortBy, sortingEnabled))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        progress.value = false
    }

}