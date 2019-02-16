package com.vkarmaedu.vkarma.fragment

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.utility.replaceFragment
import kotlinx.android.synthetic.main.fragment_student.view.*
import kotlinx.android.synthetic.main.student_content.view.*

class StudentFragment : Fragment() {

    private val TAG = javaClass.name
    private val auth by lazy { FirebaseAuth.getInstance() }
    private lateinit var authStateListener : FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authStateListener = FirebaseAuth.AuthStateListener {
            val currentUser = it.currentUser
            if (currentUser == null){
                findNavController().navigate(R.id.action_global_loginFragment)
            }
            else{

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student, container, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.setActionBar(root.toolbar)
        }
        root.student_bottom_nav.setOnNavigationItemSelectedListener(onBottomNavigationItemSelectedListener)
        root.drawer_navigation.setNavigationItemSelectedListener (onDrawerItemSelectedListener)
        activity?.let { it1 -> replaceFragment(it1, R.id.student_container, StudentProfileFragment()) }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.action_menu, menu)
    }


    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener (authStateListener)
    }

    override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener (authStateListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.notification -> {
                true
            }
            R.id.logoff -> {
                auth.signOut()
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
                R.id.profile -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, StudentProfileFragment()) }
                    true
                }
                R.id.homework -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, HomeworkFragment()) }
                    true
                }
                R.id.remark -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, RemarksFragment()) }
                    true
                }
                R.id.chat -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, ChatFragment()) }
                    true
                }
                R.id.result -> {
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
