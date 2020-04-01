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
import com.example.nikeurbandictionary.ui.CustomDialogSortFragment
import com.example.nikeurbandictionary.ui.main.recycler.SearchedWordAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_word_description.*

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

    /*
    This implementation would work with DI

    private fun showDialogFragment(dialogFragment: DialogFragment){

    }
    */

    private fun showDialogFragment(){
        val sortDialogFragment = CustomDialogSortFragment()
        val fragmentManager = supportFragmentManager
        sortDialogFragment.show(fragmentManager, "test")
    }

    override fun onOptionSelected(sortBy: Boolean) {
        this.sortBy = sortBy
        sortingEnabled = true
        viewModel.updateSearchedWordList(tvWordDescription.text.toString(), sortBy, sortingEnabled)

    }
}
