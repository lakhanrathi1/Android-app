package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.data.UserRepo
import com.vkarmaedu.vkarma.utility.replaceFragment
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import com.vkarmaedu.vkarma.utility.showSnack
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
                activity?.let { it1 -> replaceFragmentAddToBackStack(it1, LoginFragment()) }
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

        val toggle = ActionBarDrawerToggle(
            activity, root.drawer_layout, root.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        root.drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        root.drawer_navigation.setNavigationItemSelectedListener (onDrawerItemSelectedListener)
        root.student_bottom_nav.setOnNavigationItemSelectedListener (onBottomNavigationItemSelectedListener)

        root.notification.setOnClickListener {
            activity?.let { it1 -> replaceFragmentAddToBackStack(it1, NotificationFragment()) }
        }

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
                this.view?.let { showSnack(it, "notify") }
                activity?.let { replaceFragmentAddToBackStack(it, NotificationFragment()) }
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

    private val onBottomNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.homeworkFragment -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, StudentHomeworkFragment()) }
                    true
                }
                R.id.resultsFragment -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, ResultsFragment()) }
                    true
                }
                R.id.studentProfileFragment -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, StudentProfileFragment()) }
                    true
                }
                R.id.remarksFragment -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, RemarksFragment()) }
                    true
                }
                R.id.chatFragment -> {
                    activity?.let { it1 -> replaceFragment(it1, R.id.student_container, ChatListFragment()) }
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
