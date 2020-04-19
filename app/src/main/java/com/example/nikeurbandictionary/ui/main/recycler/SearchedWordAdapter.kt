package com.example.nikeurbandictionary.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.databinding.ItemWordDescriptionBinding
import com.example.nikeurbandictionary.model.SearchedWord

class SearchedWordAdapter : ListAdapter<SearchedWord, SearchedWordViewHolder>(SearchedWordDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedWordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemRowBinding = ItemWordDescriptionBinding.inflate(layoutInflater, parent, false)
        return SearchedWordViewHolder(itemRowBinding)
    }

    override fun onBindViewHolder(holder: SearchedWordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

