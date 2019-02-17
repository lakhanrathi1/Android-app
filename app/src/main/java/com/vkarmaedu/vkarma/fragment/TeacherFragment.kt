package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vkarmaedu.vkarma.R

class TeacherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_teacher, container, false)
        val navController = Navigation.findNavController(root.findViewById(R.id.nav_host_teacher))
        root.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)

        return root
    }

}
