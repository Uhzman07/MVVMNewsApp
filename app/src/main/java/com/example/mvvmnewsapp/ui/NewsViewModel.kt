package com.example.mvvmnewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnewsapp.Models.NewsResponse
import com.example.mvvmnewsapp.repository.NewsRepository
import com.example.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.Locale.IsoCountryCode

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {

    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData() // This will be used to alter data in the resource

    var breakingNewsPage = 1

    init {
        getBreakingNews("us") // To make this network call actually work, we need to give it the network permission in our manifest
    }

    // Since we want to call a function that is also a suspend function in another class, we have to make use of coroutine
    fun getBreakingNews(countryCode: String) = viewModelScope.launch {  // This is the scope belonging to this class meaning that it is active only when the class is active
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))

    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}