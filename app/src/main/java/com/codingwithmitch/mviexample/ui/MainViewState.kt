package com.codingwithmitch.mviexample.ui

import com.codingwithmitch.mviexample.model.BlogPost

data class MainViewState(

    var blogPosts: List<BlogPost> = ArrayList()
)