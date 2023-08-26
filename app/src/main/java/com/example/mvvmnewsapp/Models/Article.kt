package com.example.mvvmnewsapp.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( // This is like a table for storing something in the data base
    tableName = "articles"


)

data class Article(
    @PrimaryKey(autoGenerate = true)
    var id : Int ?= null, // This is like the primary key that is used to recognize something
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)