package com.codingwithmitch.mviexample.ui.state

sealed class MainStateEvent {

    class GetBlogPostsEvent: MainStateEvent()

    class GetUserEvent: MainStateEvent()

    class None: MainStateEvent()


}