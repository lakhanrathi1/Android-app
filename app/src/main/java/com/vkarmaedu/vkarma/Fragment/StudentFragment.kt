package com.vkarmaedu.vkarma.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vkarmaedu.vkarma.R

class StudentFragment : Fragment() {

    private val TAG = javaClass.name

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_student, container, false)
        val navController = NavHostFragment.findNavController(this)
        Log.d(TAG, "$navController")
        root.findViewById<BottomNavigationView>(R.id.student_bottom_nav).setupWithNavController(navController)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.notification -> {
                true
            }
            R.id.logoff -> {
                findNavController().navigate(R.id.action_global_loginFragment)
                true
            }
        }
        return false
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
    }
}
