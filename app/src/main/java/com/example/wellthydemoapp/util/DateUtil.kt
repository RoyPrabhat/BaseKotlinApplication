package com.example.wellthydemoapp.util

import com.example.wellthydemoapp.constant.Constants.Companion.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    val currentDate: String
        get() {
            val sdf = SimpleDateFormat(DATE_FORMAT)
            return sdf.format(Date())
        }
}
