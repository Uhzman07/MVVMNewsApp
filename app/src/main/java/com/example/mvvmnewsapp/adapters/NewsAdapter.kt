package com.example.mvvmnewsapp.adapters

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmnewsapp.Models.Article
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.databinding.ItemArticlePreviewBinding


// Diffutil calculates the differences between two lists and enable us to only update the items that were different

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // We have to create its viewHolder
    //inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ArticleViewHolder(val binding:ItemArticlePreviewBinding) : RecyclerView.ViewHolder(binding.root)

    // To create a call back
    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            // We could have used the old and new id to compare but since we could get some from our API, we have to use our url to compare
            return oldItem.url == newItem.url // This checks if the old item is equal to the new item

        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            //
            return oldItem == newItem
        }

    }
    // Async here allows it to run in the background and then "this" refers to the class "NewsAdapter"
    val differ = AsyncListDiffer(this,differCallback)


    // ---------------------- Here are the recycler view functions --------------------------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        /*
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview, // This is the layout
                parent, // This is the root
                false
            )
        )
         */
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemArticlePreviewBinding.inflate(layoutInflater,parent,false)

        return ArticleViewHolder(binding) // Here we are returning the view
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // This is used to set our views accordingly
        val article = differ.currentList[position]
        holder.binding.apply { // This is used as a reference

            Glide.with(this.root).load(article.urlToImage).into(ivArticleImage) // We are using Glide here to load our image from our article into our image view
            // Note that these are all items from "item_article_preview.xml"
            tvSource.text = article.source.name // This is used to allocate a name from the database article
            tvTitle.text = article.title // Since this is a direct attribute of the "article" class
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt

            setOnItemClickListener {
                onItemClickListener?.let{
                    it(article) // The "it" here refers to the "onItemClickListener" which is used to set the article to it for clicking
                    // What we are trying to do here is that we are trying to make one of the Article items the listener
                }
            }





        }
    }

    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article)->Unit){ // Note that "Article" is also a parameter here
        onItemClickListener = listener
    }

}

// General note
// This is not that this is different from the normal recycler view because our list here is held by our differ and call back