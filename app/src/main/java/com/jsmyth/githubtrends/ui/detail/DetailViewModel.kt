package com.jsmyth.githubtrends.ui.detail

import androidx.lifecycle.ViewModel
import com.jsmyth.githubtrends.data.Repositories
import com.jsmyth.githubtrends.data.RepositoryManager

class DetailViewModel : ViewModel() {
    val repository: Repositories.Item = RepositoryManager.currentRepository
}
