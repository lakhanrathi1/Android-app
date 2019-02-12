package com.vkarmaedu.vkarma

import androidx.multidex.MultiDexApplication
import com.google.firebase.database.FirebaseDatabase

class MyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}