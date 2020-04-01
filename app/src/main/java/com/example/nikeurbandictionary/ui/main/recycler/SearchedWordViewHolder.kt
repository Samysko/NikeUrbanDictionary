package com.example.nikeurbandictionary.ui.main.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeurbandictionary.model.SearchedWord
import kotlinx.android.synthetic.main.item_word_description.view.*

class SearchedWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var word: SearchedWord? = null
        set(value) = with(itemView) {
            field = value
            tvWordDescription.text = value?.definition
            tvLike?.text = value?.thumbs_up?.toString().orEmpty()
            tvDislike?.text = value?.thumbs_down?.toString().orEmpty()
        }
}