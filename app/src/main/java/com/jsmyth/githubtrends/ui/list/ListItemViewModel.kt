package com.jsmyth.githubtrends.ui.list

import androidx.lifecycle.ViewModel

class ListItemViewModel(private val parentViewModel: ListViewModel) : ViewModel() {
    lateinit var name: String
    lateinit var description: String

    fun itemClicked() {
        parentViewModel.onItemClicked(this)
    }
}
