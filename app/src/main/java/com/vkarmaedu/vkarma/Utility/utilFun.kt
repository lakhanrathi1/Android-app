package com.vkarmaedu.vkarma.Utility

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun replaceFragment(fragmentActivity: FragmentActivity, container: Int, fragment: Fragment){
    fragmentActivity.actionBar?.title = fragment.javaClass.name
    fragmentActivity.supportFragmentManager
        .beginTransaction()
        .replace(container, fragment)
        .commit()
}