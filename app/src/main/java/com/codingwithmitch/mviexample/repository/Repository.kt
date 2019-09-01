package com.codingwithmitch.mviexample.repository

import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.api.ApiService
import com.codingwithmitch.mviexample.api.MyRetrofitBuilder
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.ui.MainViewState
import com.codingwithmitch.util.ApiSuccessResponse
import com.codingwithmitch.util.DataState
import com.codingwithmitch.util.GenericApiResponse

object Repository {

    val apiService: ApiService = MyRetrofitBuilder
        .retrofitBuilder
        .build()
        .create(ApiService::class.java)

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<BlogPost>, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                result.value = DataState.data(
                    null,
                    MainViewState(response.body)
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                return apiService.getBlogPosts()
            }

        }.asLiveData()

    }


}
























