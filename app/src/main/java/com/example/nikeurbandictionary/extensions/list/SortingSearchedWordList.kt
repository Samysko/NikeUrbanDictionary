package com.example.nikeurbandictionary.extensions.list

import com.example.nikeurbandictionary.model.SearchedWord

fun List<SearchedWord>.sortByLikes(sortBy: Boolean, sortingEnabled: Boolean) = when {
    sortingEnabled && sortBy -> this.sortedByDescending { it.thumbs_up }
    sortingEnabled -> this.sortedByDescending { it.thumbs_down }
    else -> this
}