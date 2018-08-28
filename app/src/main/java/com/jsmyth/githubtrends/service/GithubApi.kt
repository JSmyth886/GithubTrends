package com.jsmyth.githubtrends.service

import com.jsmyth.githubtrends.data.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/repositories")
    fun getTrending(@Query("q") query: String,
                    @Query("sort") sort: String = "stars",
                    @Query("order") order: String = "desc")
            : Call<Repositories>
}