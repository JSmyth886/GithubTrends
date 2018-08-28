package com.jsmyth.githubtrends.service

import com.jsmyth.githubtrends.data.Repositories
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    private val githubApi: GithubApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        githubApi = retrofit.create(GithubApi::class.java)
    }

    fun getTrending(query: String) : Call<Repositories> {
        return githubApi.getTrending(query)
    }
}