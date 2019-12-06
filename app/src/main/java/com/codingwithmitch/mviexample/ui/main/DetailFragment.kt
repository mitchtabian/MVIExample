package com.codingwithmitch.mviexample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.model.BlogPost
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(){

    private val TAG: String = "AppDebug"

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->

            if(viewState != null){
                viewState.blogPost?.let{
                    setDetails(it)
                }
            }
        })
    }

    private fun setDetails(blogPost: BlogPost){
        val requestOptions = RequestOptions
            .overrideOf(1920, 1080)
        Glide.with(this@DetailFragment)
            .applyDefaultRequestOptions(requestOptions)
            .load(blogPost.image)
            .into(image)
        title.text = blogPost.title
        body.text = blogPost.body
    }

}

















