package com.example.nikeurbandictionary.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.ui.main.recycler.SearchedWordAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private val searchedWordAdapter by lazy { SearchedWordAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeWordObserver()
        initRecyclerView()
        initializeListeners()
    }

    private fun initializeWordObserver() {
        viewModel.searchedWordList.observe(this, Observer {
            searchedWordAdapter.submitList(it)
        })
    }

    private fun initializeListeners() {

        etWordSearch.addTextChangedListener {
            viewModel.updateSearchedWordList(it.toString(), true) }
    }

    private fun initRecyclerView() {
        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        val layoutManager = if (isPortrait) LinearLayoutManager(this) else GridLayoutManager(this, 2)

        rvWordList.layoutManager = layoutManager
        rvWordList.itemAnimator = DefaultItemAnimator()
        rvWordList.adapter = searchedWordAdapter
    }
}
