package com.example.nikeurbandictionary.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.model.SearchedWord

class SearchedWordAdapter : ListAdapter<SearchedWord, SearchedWordViewHolder>(SearchedWordDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedWordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word_description, parent, false)
        return SearchedWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchedWordViewHolder, position: Int) {
        holder.word = getItem(position)
    }
}

