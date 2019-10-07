package com.example.wellthydemoapp.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentsListResponse (
    @SerializedName("comments")
    @Expose
    var posts: ArrayList<Comment>? = null
)
