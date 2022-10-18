package com.example.tbc_final.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class BootReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action?.equals(Intent.ACTION_BOOT_COMPLETED, ignoreCase = true) == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    context.startForegroundService(Intent(context, MotionActivityService::class.java))
                }catch (e:java.lang.Exception){
                    Log.d("Receive Error", "onReceive: ${e}")
                }
            } else {
                try {
                    context.startService(Intent(context, MotionActivityService::class.java))
                }catch (e:java.lang.Exception){
                    Log.d("Receive Error", "onReceive: ${e}")
                }

            }
        }
    }
}