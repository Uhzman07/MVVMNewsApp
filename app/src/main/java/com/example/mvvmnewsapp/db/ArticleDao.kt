package com.example.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmnewsapp.Models.Article

@Dao // This is to tell that this is the interface that defines the function for us (anotation)
//Data access object
interface ArticleDao {

    // This is the function to inset or update an article
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  upsert(article: Article) : Long // This means that it returns the id of that particular article we want to insert in the data base


    // To search
    @Query("SELECT * FROM articles") // Note that this is an sql query that is used to return everything that we had selected from the table that we has created in the Article class.
    fun getAllArticles() : LiveData<List<Article>>


    // To delete an article
    @Delete
    suspend fun deleteArticle(article: Article)



}