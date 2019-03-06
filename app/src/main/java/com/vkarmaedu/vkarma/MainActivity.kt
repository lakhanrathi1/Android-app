package com.vkarmaedu.vkarma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vkarmaedu.vkarma.fragment.ChooseFragment
import com.vkarmaedu.vkarma.utility.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(this, ChooseFragment())
    }
}
