package com.codingwithmitch.mviexample.ui.main


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import com.codingwithmitch.mviexample.ui.DataStateListener
import com.codingwithmitch.mviexample.ui.main.state.MainViewState
import com.codingwithmitch.mviexample.ui.main.state.MainStateEvent
import com.codingwithmitch.mviexample.util.DataState
import com.codingwithmitch.mviexample.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(),
    MainListAdapter.Interaction
{
    override fun onItemSelected(position: Int, item: BlogPost) {
        println("DEBUG: CLICKED: $position")
        println("DEBUG: CLICKED: $item")
    }

    lateinit var viewModel: MainViewModel

    lateinit var dataStateHandler: DataStateListener

    lateinit var mainRecyclerAdapter: MainListAdapter

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
        initRecyclerView()
    }

    fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            handleDataState(dataState)
        })

        viewModel.viewState.observe(this, Observer { viewState ->
            handleViewState(viewState)
        })
    }

    fun handleDataState(dataState: DataState<MainViewState>){
        println("DEBUG: DataState: ${dataState}")
        // Handle Loading and Message
        dataStateHandler.onDataStateChange(dataState)

        // Handle Data<T>
        dataState.data?.let{ event ->
            event.getContentIfNotHandled()?.let{ viewState ->
                viewState.blogPosts?.let { blogPosts ->
                    viewModel.setBlogListData(blogPosts)
                }
                viewState.user?.let { user ->
                    viewModel.setUser(user)
                }
            }
        }
    }

    fun handleViewState(viewState: MainViewState){
        println("DEBUG: ViewState: ${viewState}")
        viewState.blogPosts?.let{ blogPosts ->
            // Set recyclerview data
            mainRecyclerAdapter.submitList(blogPosts)

        }
        viewState.user?.let{ user ->
            // Set user data
            setUserProperties(user)
        }
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            mainRecyclerAdapter = MainListAdapter(this@MainFragment)
            adapter = mainRecyclerAdapter
        }
    }

    fun setUserProperties(user: User){
        email.setText(user.email)
        username.setText(user.username)

        view?.let{
            Glide.with(it.context)
                .load(user.image)
                .into(image)
        }

    }

    fun triggerGetUserEvent(){
        viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
    }

    fun triggerGetBlogsEvent(){
        viewModel.setStateEvent(MainStateEvent.GetBlogPostsEvent())
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            dataStateHandler = context as DataStateListener
        }catch(e: ClassCastException){
            println("$context must implement DataStateChangeListener")
        }

    }

}






























