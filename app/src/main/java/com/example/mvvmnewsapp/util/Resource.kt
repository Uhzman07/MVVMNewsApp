package com.example.mvvmnewsapp.util

// Note that a "sealed class" is like na abstract class but we can decide which class to inherit from it
sealed class Resource <T>(// This is a generic type // This means that it is of the type "T"
    // This to be used to differentiate between successful and error responses
    // This is used for the 
    val data : T? = null, // This is made nullable
    val message : String? = null){

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String,data: T?=null): Resource<T>(data, message) // We did not make the string nullable because we are surely getting a response and then we might need to collect some data so we need to set it as null initially
    class Loading<T> : Resource<T>()

}