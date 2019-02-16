package com.vkarmaedu.vkarma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.findNavController(R.id.nav_host_main).addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label
        }
    }
}
