package com.example.wellthydemoapp.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("comments_count")
    @Expose
    var commentsCount: Int? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("product_state")
    @Expose
    var productState: String? = null,
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null,
    @SerializedName("votes_count")
    @Expose
    var votesCount: Int? = null,
    @SerializedName("day")
    @Expose
    var day: String? = null,
    @SerializedName("screenshot_url")
    @Expose
    var screenshotUrl: ScreenshotUrl? = null,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: Thumbnail? = null
)

