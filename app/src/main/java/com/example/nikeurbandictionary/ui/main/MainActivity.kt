package com.example.nikeurbandictionary.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeurbandictionary.NikeDictionaryApp
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.di.ViewModelFactory
import com.example.nikeurbandictionary.ui.CustomDialogSortFragment
import com.example.nikeurbandictionary.ui.main.recycler.SearchedWordAdapter
import com.example.nikeurbandictionary.util.SORT_FRAGMENT_TAG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_word_description.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CustomDialogSortFragment.DialogListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MainViewModel
    private val searchedWordAdapter by lazy { SearchedWordAdapter() }
    private var sortingEnabled: Boolean = false
    private var sortBy: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NikeDictionaryApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, this.viewModelFactory).get(MainViewModel::class.java)

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

    private fun showDialogFragment() {
        val sortDialogFragment = CustomDialogSortFragment()
        val fragmentManager = supportFragmentManager
        sortDialogFragment.show(fragmentManager, SORT_FRAGMENT_TAG)
    }

    override fun onOptionSelected(sortBy: Boolean) {
        this.sortBy = sortBy
        sortingEnabled = true
        viewModel.updateSearchedWordList(tvWordDescription.text.toString(), this.sortBy, sortingEnabled)
    }
}
