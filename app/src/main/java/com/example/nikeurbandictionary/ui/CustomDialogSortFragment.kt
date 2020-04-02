package com.example.nikeurbandictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.ui.main.MainViewModel
import com.example.nikeurbandictionary.util.THUMBS_DOWN
import com.example.nikeurbandictionary.util.THUMBS_UP
import kotlinx.android.synthetic.main.fragment_custom_dialog_sort.*

class CustomDialogSortFragment : DialogFragment(), View.OnClickListener {
    companion object {
        fun newInstance() = CustomDialogSortFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_custom_dialog_sort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    override fun onClick(v: View?) {
        val dialogListener = activity as DialogListener?
        when(v) {
            this.rbLikes -> dialogListener?.onOptionSelected(THUMBS_UP)
            this.rbDislikes -> dialogListener?.onOptionSelected(THUMBS_DOWN)
        }
        dismiss()
    }

    fun initListeners(){
        rbLikes.setOnClickListener(this)
        rbDislikes.setOnClickListener(this)
    }

    interface DialogListener{
        fun onOptionSelected(sortBy: Boolean)
    }
}
