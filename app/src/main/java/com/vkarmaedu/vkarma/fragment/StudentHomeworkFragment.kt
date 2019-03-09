package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.HomeworkStudentAdapter
import com.vkarmaedu.vkarma.utility.dateFormat
import com.vkarmaedu.vkarma.utility.replaceFragmentAddToBackStack
import kotlinx.android.synthetic.main.fragment_student_homework.view.*

class StudentHomeworkFragment : Fragment() , HomeworkStudentAdapter.OnItemClickListener{

    private lateinit var myAdapter : HomeworkStudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_student_homework, container, false)
        activity?.actionBar?.show()
        myAdapter = HomeworkStudentAdapter(this)

        root.today.visibility = getHomeworkToday()

        root.homework_recycler_view.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@StudentHomeworkFragment.context)

        }

        return root
    }

    private fun getHomeworkToday(): Int {
        return if (dateFormat.format(System.currentTimeMillis()) == "")
            View.VISIBLE
        else
            View.GONE
    }

    override fun onItemClickListener(position: Int) {
        activity?.let { replaceFragmentAddToBackStack(it, HomeworkDetailFragment()) }
    }
}
