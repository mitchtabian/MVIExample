package com.codingwithmitch.mviexample.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogPost(

    @Expose
    @SerializedName("pk")
    val pk: Int,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("body")
    val body: String,

    @Expose
    @SerializedName("image")
    val image: String
)
{
    override fun toString(): String {
        return "BlogPost(pk=$pk, title=$title, body=$body, image=$image)"
    }
}







