package com.example.nikeurbandictionary.ui.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.nikeurbandictionary.model.SearchedWord

class SearchedWordDiffItemCallback : DiffUtil.ItemCallback<SearchedWord>() {
    override fun areItemsTheSame(oldItem: SearchedWord, newItem: SearchedWord): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SearchedWord, newItem: SearchedWord): Boolean {
        return oldItem == newItem
    }
}