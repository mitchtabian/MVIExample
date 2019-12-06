package com.codingwithmitch.mviexample.repository

import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.api.MyRetrofitBuilder
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.ui.main.state.MainViewState
import com.codingwithmitch.mviexample.util.*

object Repository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<ArrayList<BlogPost>, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<ArrayList<BlogPost>>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        blogPosts = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<ArrayList<BlogPost>>> {
                return MyRetrofitBuilder.apiService.getBlogPosts()
            }

        }.asLiveData()
    }

}




























