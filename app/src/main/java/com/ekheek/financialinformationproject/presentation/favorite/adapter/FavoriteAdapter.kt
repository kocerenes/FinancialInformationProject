package com.ekheek.financialinformationproject.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ekheek.financialinformationproject.R
import com.ekheek.financialinformationproject.data.local.entity.FavoriteEntity
import com.ekheek.financialinformationproject.data.remote.model.Article
import com.ekheek.financialinformationproject.databinding.ItemNewsBinding

class FavoriteAdapter(
    private val onArticleClick: ((article: Article) -> Unit)?
) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            article: Article,
            onArticleClick: ((article: Article) -> Unit)?
        ) = binding.apply {
            imageView.load(article.urlToImage) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
            tvNewsName.text = article.title
            tvAuthor.text = article.author
            tvPublishedAt.text = article.publishedAt

            root.setOnClickListener {
                onArticleClick?.invoke(article)
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem == newItem
        }
    }

    private val diffList = AsyncListDiffer(this, diffUtil)

    var news: List<FavoriteEntity>
        get() = diffList.currentList
        set(value) = diffList.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(news[position].article, onArticleClick)
    }

    override fun getItemCount() = news.size
}