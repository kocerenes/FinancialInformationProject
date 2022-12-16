package com.ekheek.financialinformationproject.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ekheek.financialinformationproject.R
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.ItemNewsBinding

class NewsAdapter(
    private val onArticleClick: ((article: Article) -> Unit)?
) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            article: Article,
            onArticleClick: ((article: Article) -> Unit)?
        ) = binding.apply {
            imageView.load(article.urlToImage) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
            tvNewsName.text = article.title
            tvAuthor.text = article.source?.name
            tvPublishedAt.text = article.publishedAt

            root.setOnClickListener {
                onArticleClick?.invoke(article)
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this, diffUtil)

    var news: List<Article>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(news[position], onArticleClick)
    }

    override fun getItemCount() = news.size
}