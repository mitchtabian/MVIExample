package com.codingwithmitch.mviexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogPost(

    @Expose
    @SerializedName("pk")
    val pk: Int? = null,

    @Expose
    @SerializedName("title")
    val title: String? = null,

    @Expose
    @SerializedName("body")
    val body: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null
)
{
    override fun toString(): String {
        return "BlogPost(title=$title, body=$body, image=$image)"
    }
}