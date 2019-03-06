package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.utility.replaceFragment
import kotlinx.android.synthetic.main.fragment_teacher.view.*

class TeacherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_teacher, container, false)
        root.teacher_bottom_nav.setOnNavigationItemSelectedListener (onNavigationItemSelectListener)
        return root
    }

    private val onNavigationItemSelectListener = object: BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return when(item.itemId){
                R.id.homework -> {
                    activity?.let { replaceFragment(it, R.id.teacher_container, TeacherHomeworkFragment()) }
                    true
                }
                R.id.attendance ->{
                    activity?.let { replaceFragment(it, R.id.teacher_container, AttendanceFragment()) }
                    true
                }
                R.id.profile -> {
//                    activity?.let { replaceFragment(it, R.id.teacher_container, Profi()) }
                    false
                }
                R.id.resultUpload -> {
                    activity?.let { replaceFragment(it, R.id.teacher_container, ResultUploadFragment()) }
                    true
                }
                R.id.chat -> {
                    activity?.let { replaceFragment(it, R.id.teacher_container, ChatListFragment()) }
                    true
                }
                else -> false
            }
        }
    }
}
