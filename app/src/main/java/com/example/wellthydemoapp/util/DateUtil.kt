package com.example.wellthydemoapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    val currentDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(Date())
        }
}
