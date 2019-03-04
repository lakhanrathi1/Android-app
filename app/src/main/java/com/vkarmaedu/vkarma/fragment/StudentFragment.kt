package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.data.UserRepo
import com.vkarmaedu.vkarma.utility.showSnack
import kotlinx.android.synthetic.main.fragment_student.view.*
import kotlinx.android.synthetic.main.student_content.*
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
                currentUser.displayName?.let { it1 -> UserRepo.setCred(it1, currentUser.uid) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student, container, false)
        root.toolbar.inflateMenu(R.menu.action_menu)
        root.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.notification -> {
                    showSnack(nav_host_student.requireView(), "notify")
                    true
                }
                R.id.logoff -> {
                    auth.signOut()
                    true
                }
                else -> false
            }
        }
        activity?.setActionBar(root.toolbar)
        val navController = Navigation.findNavController(root.findViewById(R.id.nav_host_student))
        root.findViewById<BottomNavigationView>(R.id.student_bottom_nav)
            .setupWithNavController(navController)
        root.drawer_navigation.setNavigationItemSelectedListener (onDrawerItemSelectedListener)
        return root
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
                showSnack(nav_host_student.requireView(), "notify")
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
                R.id.fee_payment -> {
                    showSnack(this@StudentFragment.requireView(), "fee")
                    true
                }
                R.id.logout -> {
                    auth.signOut()
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
