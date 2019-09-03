package com.codingwithmitch.mviexample.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.ui.main.state.MainStateEvent
import com.codingwithmitch.mviexample.ui.main.state.MainStateEvent.*

class MainFragment : Fragment(){

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            println("DEBUG: DataState: ${dataState}")
            dataState.blogPosts?.let{
                // set BlogPosts data
                viewModel.setBlogListData(it)
            }

            dataState.user?.let{
                // set User data
                viewModel.setUser(it)
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            viewState.blogPosts?.let {
                // set BlogPosts to RecyclerView
                println("DEBUG: Setting blog posts to RecyclerView: ${viewState.blogPosts}")
            }

            viewState.user?.let{
                // set User data to widgets
                println("DEBUG: Setting User data: ${viewState.user}")
            }
        })
    }

    fun triggerGetUserEvent(){
        viewModel.setStateEvent(GetUserEvent("1"))
    }

    fun triggerGetBlogsEvent(){
        viewModel.setStateEvent(GetBlogPostsEvent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_get_blogs-> triggerGetBlogsEvent()

            R.id.action_get_user-> triggerGetUserEvent()
        }

        return super.onOptionsItemSelected(item)
    }
}














