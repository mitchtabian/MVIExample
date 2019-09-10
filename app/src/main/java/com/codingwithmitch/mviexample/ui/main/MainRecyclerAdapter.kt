package com.codingwithmitch.mviexample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.model.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*

class MainRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<BlogPost> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_blog_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<BlogPost>){
        val oldList = items
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            BlogItemDiffCallback(
                oldList,
                blogList
            )
        )
        items = blogList
        diffResult.dispatchUpdatesTo(this)
    }

    class BlogItemDiffCallback(
        var oldBlogList: List<BlogPost>,
        var newBlogList: List<BlogPost>
    ): DiffUtil.Callback(){

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldBlogList.get(oldItemPosition).pk == newBlogList.get(newItemPosition).pk)
        }

        override fun getOldListSize(): Int {
            return oldBlogList.size
        }

        override fun getNewListSize(): Int {
            return newBlogList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldBlogList.get(oldItemPosition) == newBlogList.get(newItemPosition))
        }

    }

    class BlogViewHolder
        constructor(
            itemView: View
        ) : RecyclerView.ViewHolder(itemView)
    {
        val blog_image = itemView.blog_image
        val blog_title = itemView.blog_title

        fun bind(blogPost: BlogPost){
            blog_title.setText(blogPost.title)

            Glide.with(itemView.context)
                .load(blogPost.image)
                .into(blog_image)
        }
    }


}















