package com.bootup

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import android.util.Log
import android.content.ActivityNotFoundException

class BootReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "BootReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Toast.makeText(context, "Boot Completed. Starting your app...", Toast.LENGTH_SHORT).show()

            // Launch the other app here
            val launchIntent2 = context.packageManager.getLaunchIntentForPackage("com.bootup")
            Log.d(TAG, "Launch Intent: $launchIntent2")

            if (launchIntent2 != null) {
                Toast.makeText(context, "$launchIntent2 is not null. Starting your app...", Toast.LENGTH_SHORT).show()

                try {
                    Log.d(TAG, "App about to launch...")
                    context.startActivity(launchIntent2)
                    Log.d(TAG, "App launched successfully")
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Activity not found for intent", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Activity not found for intent: $launchIntent2", e)
                }
            }
        }
    }
}
