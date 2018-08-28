package com.jsmyth.githubtrends.ui.list

import androidx.lifecycle.ViewModel
import com.jsmyth.githubtrends.data.Repositories

class ListItemViewModel(private val parentViewModel: ListViewModel, val repository: Repositories.Item) : ViewModel() {

    fun itemClicked() {
        parentViewModel.onItemClicked(this)
    }
}
