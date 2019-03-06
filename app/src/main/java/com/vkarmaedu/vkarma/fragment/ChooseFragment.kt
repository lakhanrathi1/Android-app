package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.utility.replaceFragment
import kotlinx.android.synthetic.main.fragment_choose.view.*

class ChooseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_choose, container, false)

        root.student_button.setOnClickListener {
            activity?.let { it1 -> replaceFragment(it1, StudentFragment()) }
            Log.d(TAG, "student button")
        }
        root.teacher_button.setOnClickListener {
            activity?.let { it1 -> replaceFragment(it1, TeacherFragment()) }
        }
        root.parent_button.setOnClickListener {
        }
        return root
    }

    companion object {
        private const val TAG = "ChooseFragment"
    }
}
