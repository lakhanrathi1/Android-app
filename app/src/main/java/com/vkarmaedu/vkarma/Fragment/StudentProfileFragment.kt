package com.vkarmaedu.vkarma.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.ViewModel.StudentProfileViewModel

class StudentProfileFragment : Fragment() {

    companion object {
        fun newInstance() = StudentProfileFragment()
    }

    private lateinit var viewModel: StudentProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StudentProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
