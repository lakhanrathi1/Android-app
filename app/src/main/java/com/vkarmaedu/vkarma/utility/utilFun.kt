package com.vkarmaedu.vkarma.utility

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.vkarmaedu.vkarma.R
import java.text.SimpleDateFormat
import java.util.*

fun replaceFragment(fragmentActivity: FragmentActivity?, fragment: Fragment){
    if (fragmentActivity == null) return
    fragmentActivity.actionBar?.title = fragmentActivity.title

    fragmentActivity.supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        .replace(R.id.main_container, fragment)
        .commit()
}

fun replaceFragment(fragmentActivity: FragmentActivity?, container: Int, fragment: Fragment){
    if (fragmentActivity == null) return
    fragmentActivity.actionBar?.title = fragmentActivity.title
    val f = fragmentActivity.supportFragmentManager.findFragmentById(container)

    if (f != null && f.javaClass == fragment.javaClass){
        return
    }

    fragmentActivity.supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        .replace(container, fragment)
        .commit()
}

fun replaceFragmentAddToBackStack(fragmentActivity: FragmentActivity?, fragment: Fragment){
    if (fragmentActivity == null) return
    fragmentActivity.actionBar?.title = fragmentActivity.title
    fragmentActivity.supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        .replace(R.id.main_container, fragment)
        .addToBackStack(null)
        .commit()
}

fun popBackStack(fragmentActivity: FragmentActivity){
    fragmentActivity.supportFragmentManager.popBackStack()
}

fun showSnack(view : View, message : String){
    Snackbar.make(view , message, Snackbar.LENGTH_SHORT).show()
}

fun hideKeyboard(activity: FragmentActivity) {
    val view = activity.currentFocus
    if (view != null) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun getTimeDiff(thatTime : Long) : String{
    val timeDiff = (System.currentTimeMillis() - thatTime) / 60000
    return when{
        timeDiff <= 60 -> "$timeDiff min before"
        timeDiff <= 3600 -> "${timeDiff / 60} hrs before"
        timeDiff <= 86400 -> "${timeDiff / 1440} days before"
        else -> SimpleDateFormat("dd/mm/yy", Locale.getDefault()).format(thatTime)
    }
}

val dateFormat = SimpleDateFormat("dd mmm yyyy", Locale.getDefault())
val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())