package com.example.mvvmnewsapp.repository

import com.example.mvvmnewsapp.api.RetrofitInstance
import com.example.mvvmnewsapp.db.ArticleDatabase

class NewsRepository( // The purpose is to get the data from out database
    val db: ArticleDatabase
) {
    // Note that we can only call a suspend from inside another suspend function or a coroutine
    suspend fun getBreakingNews(countryCode:String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}