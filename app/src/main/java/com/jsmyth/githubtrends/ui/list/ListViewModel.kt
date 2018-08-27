package com.jsmyth.githubtrends.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    val navigateToDetails: MutableLiveData<ListItemViewModel> = MutableLiveData()

    fun onItemClicked(item: ListItemViewModel) {
        navigateToDetails.value = item
    }
}
