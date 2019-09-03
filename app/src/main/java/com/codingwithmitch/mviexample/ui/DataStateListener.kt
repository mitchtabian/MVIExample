package com.codingwithmitch.mviexample.ui

import com.codingwithmitch.mviexample.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}