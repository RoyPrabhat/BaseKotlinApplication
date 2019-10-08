package com.example.wellthydemoapp.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("body")
    @Expose
    var body: String? = null,
    @SerializedName("created_at")
    @Expose
    var creationTime: String? = null,
    @SerializedName("votes")
    @Expose
    var votes: Int? = null
)