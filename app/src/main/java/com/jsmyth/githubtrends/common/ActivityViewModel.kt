package com.jsmyth.githubtrends.common

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

open class ActivityViewModel : ViewModel() {

    private val title = ObservableField<String>()

    fun getTitle(): ObservableField<String> {
        return title
    }

    fun setTitle(title: String) {
        this.title.set(title)
    }
}