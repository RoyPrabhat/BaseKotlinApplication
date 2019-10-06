package com.example.wellthydemoapp.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ScreenshotUrl(
    @SerializedName("300px")
    @Expose
    var small: String? = null,
    @SerializedName("850px")
    @Expose
    var large: String? = null
)
