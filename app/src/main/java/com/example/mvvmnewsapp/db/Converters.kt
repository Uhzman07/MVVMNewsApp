package com.example.mvvmnewsapp.db

import androidx.room.TypeConverter
import com.example.mvvmnewsapp.Models.Source

// This is used to know the source of the news and also to return the source
class Converters {

    @TypeConverter // This is used the get the name of that source
    fun fromSource(source: Source) : String{
        // Since we are trying to get some info from the source class
        return source.name

    }
    @TypeConverter // This is used to derive the source from the name that we are given
    fun toSource(name: String)  : Source { // This is expected to return our source class
        return Source(name,name)
    }
}