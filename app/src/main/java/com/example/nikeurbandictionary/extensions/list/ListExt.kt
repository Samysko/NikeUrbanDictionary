package com.example.nikeurbandictionary.extensions.list

import com.example.nikeurbandictionary.model.SearchedWord

/**
 * Used to sort a Searched Word List by likes, dislikes otherwise it will return the default list's order.
 *
 * @param sortBy Indicates weather the list will be sorted by number of thumbs up or thumbs down
 * @param sortingEnabled Indicates if the sort is enabled
 */
fun List<SearchedWord>.sortByLikes(sortBy: Boolean, sortingEnabled: Boolean) = when {
    sortingEnabled && sortBy -> this.sortedByDescending { it.thumbs_up }
    sortingEnabled -> this.sortedByDescending { it.thumbs_down }
    else -> this
}