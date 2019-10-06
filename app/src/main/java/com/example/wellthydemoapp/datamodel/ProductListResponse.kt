package com.example.wellthydemoapp.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductListResponse(
    @SerializedName("posts")
    @Expose
    var posts: ArrayList<Post>? = null)
