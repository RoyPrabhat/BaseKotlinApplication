package com.example.wellthydemoapp.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

object ToastUtil {

    fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}