package com.example.wellthydemoapp.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Thumbnail {
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null
}
