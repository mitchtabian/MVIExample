package com.codingwithmitch.mviexample.ui.main

import androidx.fragment.app.FragmentFactory

class MainFragmentFactory : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            MainFragment::class.java.name -> {
                MainFragment()
            }

            DetailFragment::class.java.name -> {
                DetailFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}