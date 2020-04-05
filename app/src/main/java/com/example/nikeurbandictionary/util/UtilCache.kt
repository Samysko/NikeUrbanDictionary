package com.example.nikeurbandictionary.util

import com.example.nikeurbandictionary.model.SearchedWord

class UtilCache {
    companion object {
        private val mapCache: MutableMap<String, List<SearchedWord>> = HashMap<String, List<SearchedWord>>()

        fun getCache(key: String): List<SearchedWord>? {
            return mapCache[key]
        }

        fun addToCache(key: String, wordSearchedList: List<SearchedWord>){
            if (wordSearchedList.isNotEmpty()) mapCache[key] = wordSearchedList
        }
    }
}