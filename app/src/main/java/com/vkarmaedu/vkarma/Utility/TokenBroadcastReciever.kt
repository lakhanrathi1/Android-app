package com.vkarmaedu.vkarma.Utility

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

abstract class TokenBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive:$intent")

        if (ACTION_TOKEN == intent.action) {
            val token = intent.extras?.getString(EXTRA_KEY_TOKEN)
            onNewToken(token)
        }
    }

    abstract fun onNewToken(token: String?)

    companion object {
        const val ACTION_TOKEN = "com.google.example.ACTION_TOKEN"
        val filter: IntentFilter
            get() = IntentFilter(ACTION_TOKEN)
        private const val TAG = "TokenBroadcastReceiver"
        const val EXTRA_KEY_TOKEN = "key_token"
    }
}