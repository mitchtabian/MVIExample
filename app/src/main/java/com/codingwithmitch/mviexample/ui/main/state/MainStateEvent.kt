package com.codingwithmitch.mviexample.ui.main.state

sealed class MainStateEvent {

    class GetBlogPostsEvent: MainStateEvent()

    class None: MainStateEvent()

}