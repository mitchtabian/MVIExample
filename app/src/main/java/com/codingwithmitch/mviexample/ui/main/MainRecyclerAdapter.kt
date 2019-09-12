package com.codingwithmitch.mviexample.ui.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.model.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*

class MainRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    val DIFF_CALLBACK = object: DiffUtil.ItemCallback<BlogPost>(){

        override fun areItemsTheSame(oldItem: BlogPost, newItem: BlogPost): Boolean {
            return oldItem.pk == newItem.pk
        }

        override fun areContentsTheSame(oldItem: BlogPost, newItem: BlogPost): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


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
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(blogList: List<BlogPost>){
        differ.submitList(blogList)
    }

    class BlogViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)
    {

        fun bind(blogPost: BlogPost) = with(itemView){
            itemView.blog_title.text = blogPost.title

            Glide.with(itemView.context)
                .load(blogPost.image)
                .into(itemView.blog_image)
        }
    }




}
















