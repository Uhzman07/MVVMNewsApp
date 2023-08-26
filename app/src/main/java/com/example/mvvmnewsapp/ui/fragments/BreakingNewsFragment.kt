package com.example.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.adapters.NewsAdapter
import com.example.mvvmnewsapp.databinding.FragmentBreakingNewsBinding
import com.example.mvvmnewsapp.ui.NewsActivity
import com.example.mvvmnewsapp.ui.NewsViewModel
import com.example.mvvmnewsapp.util.Resource

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel : NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentBreakingNewsBinding

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity). viewModel // This because we want to make use of the view model of the main class

        binding = FragmentBreakingNewsBinding.bind(view) // This is used for binding

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->  // This is just the name that is used to represent the response
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }

                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message->
                        Log.e(TAG,"An error occured: $message")

                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }

            }
        })

    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter() // We are creating our new adapter class here
        binding.rvBreakingNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity) // Here we are creating a new layout manager for it

        }


    }

}