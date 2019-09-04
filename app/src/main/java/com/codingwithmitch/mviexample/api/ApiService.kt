package com.codingwithmitch.mviexample.api

import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getBlogPosts(): List<BlogPost>

    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): User
}