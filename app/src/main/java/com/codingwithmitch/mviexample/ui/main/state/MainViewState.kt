package com.codingwithmitch.mviexample.ui.main.state

import com.codingwithmitch.mviexample.model.BlogPost

data class MainViewState(

    var blogPosts: ArrayList<BlogPost> = ArrayList<BlogPost>(),

    var blogPost: BlogPost? = null

)