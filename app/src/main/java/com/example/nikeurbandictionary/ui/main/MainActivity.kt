package com.example.nikeurbandictionary.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.ui.CustomDialogSortFragment
import com.example.nikeurbandictionary.ui.main.recycler.SearchedWordAdapter
import com.example.nikeurbandictionary.util.SORT_FRAGMENT_TAG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_word_description.*
import kotlinx.coroutines.delay
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt

class MainActivity : AppCompatActivity(), CustomDialogSortFragment.DialogListener {
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private val searchedWordAdapter by lazy { SearchedWordAdapter() }

    private var sortingEnabled: Boolean = false
    private var sortBy: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ibSort.setOnClickListener {
            showDialogFragment()
        }

        initializeWordObserver()
        progressDialogObserver()
        initRecyclerView()
        initializeListeners()
    }

    private fun initializeWordObserver() {
        viewModel.searchedWordList.observe(this, Observer {
            searchedWordAdapter.submitList(it)
        })
    }

    private fun progressDialogObserver() {
        viewModel.progress.observe(this, Observer {
            if (it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.INVISIBLE
        })
    }

    private fun initializeListeners() {
        etWordSearch.addTextChangedListener {
            viewModel.updateSearchedWordList(it.toString(), sortBy, sortingEnabled)
        }
    }

    private fun initRecyclerView() {
        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        val layoutManager = if (isPortrait) LinearLayoutManager(this) else GridLayoutManager(this, 2)

        rvWordList.layoutManager = layoutManager
        rvWordList.itemAnimator = DefaultItemAnimator()
        rvWordList.adapter = searchedWordAdapter
    }

    private fun showDialogFragment(){
        val sortDialogFragment = CustomDialogSortFragment()
        val fragmentManager = supportFragmentManager
        sortDialogFragment.show(fragmentManager, SORT_FRAGMENT_TAG)
    }

    override fun onOptionSelected(sortBy: Boolean) {
        this.sortBy = sortBy
        this.sortingEnabled = true
        viewModel.updateSearchedWordList(tvWordDescription.text.toString(), sortBy, sortingEnabled)
    }
}
