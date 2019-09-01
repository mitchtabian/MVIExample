package com.codingwithmitch.mviexample.api

import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.util.GenericApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("placeholder/blogs")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>

}





