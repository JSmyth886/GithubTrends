package com.jsmyth.githubtrends.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    val navigateToDetails: MutableLiveData<Boolean> = MutableLiveData()

    fun onDetailClick() {
        navigateToDetails.value = true
    }
}
