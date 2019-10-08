package com.example.wellthydemoapp.util

import android.content.Context
import android.net.ConnectivityManager
import com.example.wellthydemoapp.datamodel.Post

object DataFilterUtility {

    fun filterData(posts: ArrayList<Post>, name : String = "",
                   tagLine: String = "" ): ArrayList<Post> {
        var fiterList = posts!!.filter { it.name!!.contains(name)  && it.tagline!!.contains(tagLine)}
        return ArrayList(fiterList)
    }

    fun filterDataByName(posts: ArrayList<Post>, name : String = ""): ArrayList<Post> {
        var fiterList = posts!!.filter { it.name!!.contains(name) }
        return ArrayList(fiterList)
    }

    fun filterDataByTagline(posts: ArrayList<Post>, tagLine: String = "" ): ArrayList<Post> {
        var fiterList = posts!!.filter { it.tagline!!.contains(tagLine) }
        return ArrayList(fiterList)
    }

}