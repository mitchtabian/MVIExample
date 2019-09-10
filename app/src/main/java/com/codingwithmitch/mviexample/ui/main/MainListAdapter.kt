package com.codingwithmitch.mviexample.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.model.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*

class MainListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<BlogPost, MainListAdapter.BlogPostViewHolder>(BlogPostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlogPostViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_blog_list_item, parent, false), interaction
    )

    override fun onBindViewHolder(holder: BlogPostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BlogPostViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            interaction?.onItemSelected(adapterPosition, getItem(adapterPosition))
        }

        fun bind(item: BlogPost) = with(itemView) {
            blog_title.setText(item.title)

            Glide.with(this)
                .load(item.image)
                .into(blog_image)
        }
    }

    interface Interaction {

        fun onItemSelected(position: Int, blogPost: BlogPost)
    }

    private class BlogPostDiffCallback : DiffUtil.ItemCallback<BlogPost>() {

        override fun areItemsTheSame(
            oldItem: BlogPost,
            newItem: BlogPost
        ): Boolean {
            return (oldItem.pk == newItem.pk)
        }

        override fun areContentsTheSame(
            oldItem: BlogPost,
            newItem: BlogPost
        ): Boolean {
            return oldItem == newItem
        }
    }
}




