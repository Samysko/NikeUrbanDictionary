package com.example.nikeurbandictionary.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchedWord(
    val definition: String,
    val thumbs_up: Int,
    val thumbs_down: Int,
    val example: String
)