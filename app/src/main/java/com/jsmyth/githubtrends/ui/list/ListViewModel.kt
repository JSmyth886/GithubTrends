package com.jsmyth.githubtrends.ui.list

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jsmyth.githubtrends.data.Repositories
import com.jsmyth.githubtrends.service.ApiService
import com.jsmyth.githubtrends.data.RepositoryManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ListViewModel : ViewModel() {

    val loadingRepos: ObservableField<Boolean> = ObservableField()
    val noSearchResults: ObservableField<Boolean> = ObservableField()
    val apiError: ObservableField<Boolean> = ObservableField()

    val repositoryList: MutableLiveData<MutableList<ListItemViewModel>> = MutableLiveData()
    val navigateToDetails: MutableLiveData<ListItemViewModel> = MutableLiveData()

    init {
        loadingRepos.set(true)
        apiError.set(false)
        noSearchResults.set(false)
        RepositoryManager

        //Search for Repo's with the keyword Android and created date of 1 week ago
        val query = "Android+created:" + getDaysAgo(7)
        val api = ApiService()
        api.getTrending(query).enqueue(object : Callback<Repositories> {
            override fun onFailure(call: Call<Repositories>?, t: Throwable?) {
                loadingRepos.set(false)
                apiError.set(true)
            }

            override fun onResponse(call: Call<Repositories>?, response: Response<Repositories>?) {
                if (!response!!.isSuccessful || response.body()!!.items.isEmpty()) {
                    loadingRepos.set(false)
                    noSearchResults.set(true)
                    return
                }

                val list: MutableList<ListItemViewModel> = ArrayList()
                for (data in  response.body()!!.items) {
                    val item = ListItemViewModel(this@ListViewModel, data)
                    list.add(item)
                }

                repositoryList.value = list
                loadingRepos.set(false)
            }
        })
    }

    fun onItemClicked(item: ListItemViewModel) {
        RepositoryManager.currentRepository = item.repository
        navigateToDetails.value = item
    }

    @SuppressLint("SimpleDateFormat")
    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val format = SimpleDateFormat("yyy-MM-dd")
        return format.format(calendar.time)
    }
}