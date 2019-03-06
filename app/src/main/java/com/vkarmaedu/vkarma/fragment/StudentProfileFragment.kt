package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.StudentProfilePagerAdapter
import com.vkarmaedu.vkarma.viewModel.StudentProfileViewModel
import kotlinx.android.synthetic.main.fragment_student_profile.view.*

class StudentProfileFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentProfileViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_profile, container, false)
        activity?.let {
            it.actionBar?.hide()
            root.viewpager.adapter = StudentProfilePagerAdapter(it.supportFragmentManager)
        }

        root.tabLayout.setupWithViewPager(root.viewpager)
        return root
    }
}
