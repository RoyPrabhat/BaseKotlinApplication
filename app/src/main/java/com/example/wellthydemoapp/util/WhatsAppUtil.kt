package com.example.wellthydemoapp.util

import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

object WhatsAppUtil {

    fun sendWhatAppMessage(activity: FragmentActivity?, message: String? = "Dummy message") {
        val pm = activity!!.packageManager
        try {
            var waIntent = Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");

            var info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, message);
            activity.startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (exception: PackageManager.NameNotFoundException) {
            Toast.makeText(activity, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                .show();
        } catch (e: Exception) {
            e.toString();
        }

    }
}