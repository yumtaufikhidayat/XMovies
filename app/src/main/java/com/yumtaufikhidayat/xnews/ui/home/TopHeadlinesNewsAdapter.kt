package com.yumtaufikhidayat.xnews.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yumtaufikhidayat.xnews.R
import com.yumtaufikhidayat.xnews.databinding.ItemNewsBinding
import com.yumtaufikhidayat.xnews.model.Article
import com.yumtaufikhidayat.xnews.utils.covertDateToTime
import com.yumtaufikhidayat.xnews.utils.loadImage

class TopHeadlinesNewsAdapter(
    private val onItemClickListener: (Article) -> Unit
) : PagingDataAdapter<Article, TopHeadlinesNewsAdapter.ViewHolder>(
    topHeadlinesNewsDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Article) {
            binding.apply {
                imgTopHeadlinesNews.loadImage(data.urlToImage)
                tvNewsTitle.text = data.title
                tvNewsDesc.text = data.description
                tvNewsSource.text = itemView.context.getString(
                    R.string.tvSourceDate,
                    data.source?.name,
                    covertDateToTime(data.publishedAt)
                )
                constraintTopStoriesTopHeadlinesNews.setOnClickListener {
                    onItemClickListener(data)
                }
            }
        }
    }

    companion object {
        val topHeadlinesNewsDiffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(
                oldItem: Article,
                newItem: Article
            ): Boolean = oldItem.publishedAt == newItem.publishedAt

            override fun areContentsTheSame(
                oldItem: Article,
                newItem: Article
            ): Boolean = oldItem == newItem
        }
    }
}