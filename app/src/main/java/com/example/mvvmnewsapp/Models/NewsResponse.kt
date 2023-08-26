package com.example.mvvmnewsapp.Models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)