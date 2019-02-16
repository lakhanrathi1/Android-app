package com.vkarmaedu.vkarma.utility

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

fun replaceFragment(fragmentActivity: FragmentActivity, container: Int, fragment: Fragment){
    fragmentActivity.actionBar?.title = fragmentActivity.title
    fragmentActivity.supportFragmentManager
        .beginTransaction()
        .replace(container, fragment)
        .commit()
}

fun replaceFragmentAddToBackStack(fragmentActivity: FragmentActivity, container: Int, fragment: Fragment){
    fragmentActivity.actionBar?.title = fragmentActivity.title
    fragmentActivity.supportFragmentManager
        .beginTransaction()
        .replace(container, fragment)
//        .addToBackStack()
        .commit()
}

fun showSnack(view : View, message : String){
    Snackbar.make(view , message, Snackbar.LENGTH_SHORT).show()
}