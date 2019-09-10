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

    override fun equals(other: Any?): Boolean {

        if(javaClass != other?.javaClass){
            return false
        }

        other as BlogPost

        if(pk != other.pk){
            return false
        }
        if(title != other.title){
            return false
        }
        if(body != other.body){
            return false
        }
        if(image != other.image){
            return false
        }


        return true
    }

    override fun toString(): String {
        return "BlogPost(title=$title, body=$body, image=$image)"
    }
}







