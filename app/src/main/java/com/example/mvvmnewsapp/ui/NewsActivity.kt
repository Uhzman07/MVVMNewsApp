package com.example.mvvmnewsapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.example.mvvmnewsapp.databinding.ActivityNewsBinding
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.db.ArticleDatabase
import com.example.mvvmnewsapp.repository.NewsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.accessibility.AccessibilityNodeInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding

    // For the ViewModel
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        //val view = binding.root
        setContentView(binding.root)

        // To create a new nav graph
        // res- right click -> android resource file -> Navigation
        // Note that if we are going to put a nav graph in a fragment, we have to give the fragment containing it a very correct name as an attribute

        //binding.bottomNavigationView.setupWithNavController(binding.newsNavHostFragment.findNavController())



        // To link the fragment with the bottom navigation view
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)



        // To get the news api, we go to the newsapi.org then copy the code there
        // then we go to the project folder and create a new json to kotlin file where we paste the code and then 3 classes are generated for us
        // Note that the classes NewsResponse.kt, Source.kt and Article.kt

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[NewsViewModel :: class.java]





        /*
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info?.text = "This is our floating button"
            }
        }

         */









    }
}


/*
setContent {
            MVVMNewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVMNewsAppTheme {
        Greeting("Android")
    }
}
 */