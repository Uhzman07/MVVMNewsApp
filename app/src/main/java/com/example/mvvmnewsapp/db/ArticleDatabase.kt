package com.example.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mvvmnewsapp.Models.Article
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// Note that the Database class has to be always abstract because it is from "room"
@Database(
    entities = [Article::class],
    version = 1, // We are telling room just in case we want to upgrade the version later


)
// Then to link all the Converters to the database
@TypeConverters(Converters::class)

abstract class ArticleDatabase : RoomDatabase(){ // This is inheriting from the RoomDatabase

    // For a function that returns Article Dao
    abstract fun getArticleDao() : ArticleDao

    companion object{
        @Volatile // This annotation is very useful so as to allow other threads to see this even if it is changed by another thread
        private var instance : ArticleDatabase ? = null // Here we are creating an instance of that class
        private val LOCK = Any() // This is used to make sure that we only have an instance of the database at once
        //  LOCK is a synchronization object used to ensure that only one thread can access the instance creation logic at a time.

        // Note that this operator function "invoke()" means that it runs directly as soon as the class is called

        @OptIn(InternalCoroutinesApi::class) // This is auto generated
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {// Note that this means that anything that happens inside our block of code here won't be accessed by any other one
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, // This is the context
                ArticleDatabase::class.java, // This is the database
                "article_db.db" // This is the name given to it
            ).build()
    }

}