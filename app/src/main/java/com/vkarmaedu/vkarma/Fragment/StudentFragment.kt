package com.vkarmaedu.vkarma.Fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.Utility.replaceFragment
import kotlinx.android.synthetic.main.fragment_student.view.*
import kotlinx.android.synthetic.main.student_content.view.*

class StudentFragment : Fragment() {

    private val TAG = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().actionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_student, container, false)
        root.student_bottom_nav.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener)
        root.drawer_navigation.setNavigationItemSelectedListener (onDrawerItemSelectedListener)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification -> {
                true
            }
            R.id.logoff -> {
                findNavController().navigate(R.id.action_global_loginFragment)
                true
            }
            else -> false
        }
    }

    private val onDrawerItemSelectedListener = object : NavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return when(item.itemId){
                R.id.student_fee_payment -> {
                    true
                }
                else -> false
            }
        }

    }

    private val onBottomNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.student_menu_profile -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, StudentProfileFragment()) }
                    true
                }
                R.id.student_menu_homework -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, HomeworkFragment()) }
                    true
                }
                R.id.student_menu_remark -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, RemarksFragment()) }
                    true
                }
                R.id.student_menu_chat -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, ChatFragment()) }
                    true
                }
                R.id.student_menu_result -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, ResultsFragment()) }
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
    }
}
