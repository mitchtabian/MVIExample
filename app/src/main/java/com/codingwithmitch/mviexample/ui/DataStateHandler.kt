package com.codingwithmitch.mviexample.ui

import com.codingwithmitch.mviexample.util.DataState

interface DataStateHandler {

    fun onDataStateChange(dataState: DataState<*>?)
}