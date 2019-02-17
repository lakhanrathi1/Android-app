package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vkarmaedu.vkarma.R
import kotlinx.android.synthetic.main.fragment_choose.view.*

class ChooseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_choose, container, false)

        root.student_button.setOnClickListener {
            root.findNavController().navigate(R.id.action_chooseFragment_to_studentFragment)
        }
        root.teacher_button.setOnClickListener {
            root.findNavController().navigate(R.id.action_chooseFragment_to_teacherFragment)
        }
        root.parent_button.setOnClickListener {
        }
        return root
    }

}
