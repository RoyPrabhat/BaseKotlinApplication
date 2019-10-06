package com.example.wellthydemoapp.util

import android.content.Context
import android.net.ConnectivityManager

object ConnectivityUtil {

     fun isNetworkConnected(applicationContext: Context): Boolean {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

}
