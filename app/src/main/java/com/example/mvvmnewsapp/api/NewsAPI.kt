package com.example.mvvmnewsapp.api

import com.example.mvvmnewsapp.Models.NewsResponse
import com.example.mvvmnewsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines") // This is the url that we are sending something to

    suspend fun getBreakingNews(
        @Query("country") // Note that we can get all these from the documentation of the API
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1, // Note that we are setting the page number to 1 initially
        @Query("apiKey") // This is used to know who made the request
        apiKey: String = API_KEY
    // Note that we also have to add the response class to it.
    // We must also import the retrofit import instead of the ohkttp
    ): Response<NewsResponse> // This is the response that was generated from the API from json to kotlin file

    // For the search request
    // Here we are searching every part of the news
    @GET("v2/everything")

    suspend fun searchForNews(
        @Query("q") // This is useful when searching for a particular string or something
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1, // Note that we are setting the page number to 1 initially
        @Query("apiKey") // This is used to know who made the request
        apiKey: String = API_KEY
        // Note that we also have to add the response class to it.
        // We must also import the retrofit import instead of the ohkttp
    ): Response<NewsResponse> // This is the response that was generated from the API from json to kotlin file

}