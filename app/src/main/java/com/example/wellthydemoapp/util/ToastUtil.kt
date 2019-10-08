package com.example.wellthydemoapp.util

import android.widget.Toast
import androidx.fragment.app.FragmentActivity

object ToastUtil {

    fun showToast(context: FragmentActivity?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}