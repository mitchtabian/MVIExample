package com.codingwithmitch.mviexample.ui

sealed class StateEvent {

    class GetBlogPosts: StateEvent()

    class None: StateEvent()


}