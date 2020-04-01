package com.example.nikeurbandictionary.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchedWordList(val list: List<SearchedWord>)