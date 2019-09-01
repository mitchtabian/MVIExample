package com.codingwithmitch.mviexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.ui.StateEvent.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        subscribeObservers()
        viewModel.setStateEvent(GetBlogPosts())
    }

    fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            println("DEBUG: DataState: ${dataState}")
            dataState.data?.let{
                viewModel.setBlogListData(it.blogPosts)
            }
        })

        viewModel.viewState.observe(this, Observer { viewState ->
            println("DEBUG: ViewState: ${viewState}")
            viewState.blogPosts.let{
                TODO("set RecyclerView data")
            }
        })
    }
}























