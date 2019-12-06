package com.codingwithmitch.mviexample.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable {
    override fun toString(): String {
        return "BlogPost(pk=$pk, title=$title, body=$body, image=$image)"
    }
}







