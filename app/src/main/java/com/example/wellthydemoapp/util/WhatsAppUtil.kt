package com.example.wellthydemoapp.util

import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.wellthydemoapp.R

object WhatsAppUtil {

    val whatAppPackageName = "com.whatsapp"
    val messageType = "text/plain"
    val title = "Share with"

    fun sendWhatAppMessage(activity: FragmentActivity?, message: String) {
        val pm = activity!!.packageManager
        try {
            var waIntent = Intent(Intent.ACTION_SEND);
            waIntent.setType(messageType);

            var info = pm.getPackageInfo(whatAppPackageName, PackageManager.GET_META_DATA);

            waIntent.setPackage(whatAppPackageName);

            waIntent.putExtra(Intent.EXTRA_TEXT, message);
            activity.startActivity(Intent.createChooser(waIntent, title));

        } catch (exception: PackageManager.NameNotFoundException) {
            ToastUtil.showToast(activity, activity.getString(R.string.no_whatsapp))
        } catch (e: Exception) {
            e.toString();
        }

    }
}