package com.example.nikeurbandictionary.ui.main.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.nikeurbandictionary.databinding.ItemWordDescriptionBinding
import com.example.nikeurbandictionary.model.SearchedWord

class SearchedWordViewHolder(
    private val itemWordDescriptionBinding: ItemWordDescriptionBinding)
    : RecyclerView.ViewHolder(itemWordDescriptionBinding.root) {

    fun bind(searchedWord: SearchedWord) {
        itemWordDescriptionBinding.searchedWord = searchedWord
        itemWordDescriptionBinding.executePendingBindings()
    }
}