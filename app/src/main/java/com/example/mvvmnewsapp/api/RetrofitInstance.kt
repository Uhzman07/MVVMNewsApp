package com.example.mvvmnewsapp.api

import com.example.mvvmnewsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy{ // "lazy" here means that we are only making use of it once
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY) // This means that the body is displayed initially

            val client  = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Note that this is used to convert to a kotlin file
                .build()

        }
        val api by lazy{ // note that this the api that we will use to generate a network response from anywhere in our project
            retrofit.create(NewsAPI::class.java) // Here we are adding the interface taht
        }
    }
}