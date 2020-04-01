package com.example.nikeurbandictionary.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikeurbandictionary.model.SearchedWord
import com.example.nikeurbandictionary.service.ResponseUrbanApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val searchedWordList = MutableLiveData<List<SearchedWord>>()

    fun updateSearchedWordList(term: String, sort: Boolean, isOrdered: Boolean = false) = viewModelScope.launch {
        try {
            val repository = ResponseUrbanApi()
            val listSearchedWord = repository.retrieveWordDefinitions(term)
            searchedWordList.value = when {
                isOrdered && sort -> listSearchedWord.list.sortedBy { it.thumbs_up }
                isOrdered -> listSearchedWord.list.sortedByDescending { it.thumbs_up }
                else -> listSearchedWord.list
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}