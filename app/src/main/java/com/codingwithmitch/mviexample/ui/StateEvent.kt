package com.codingwithmitch.mviexample.ui

sealed class StateEvent {

    class GetBlogPostsEvent: StateEvent()

    class GetUserEvent: StateEvent()

    class None: StateEvent()


}