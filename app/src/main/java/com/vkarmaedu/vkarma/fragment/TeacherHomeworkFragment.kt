package com.vkarmaedu.vkarma.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.HomeworkTeacherAdapter
import com.vkarmaedu.vkarma.viewModel.TeacherHomeworkViewModel
import kotlinx.android.synthetic.main.fragment_teacher_homework.view.*

class TeacherHomeworkFragment : Fragment(), HomeworkTeacherAdapter.OnItemClickListener {

    private lateinit var myAdapter : HomeworkTeacherAdapter
    private val viewmodel by lazy { ViewModelProviders.of(this).get(TeacherHomeworkViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_teacher_homework, container, false)

        myAdapter = HomeworkTeacherAdapter(this)

        root.homework_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@TeacherHomeworkFragment.context)
            adapter = myAdapter
        }

        root.new_homework.setOnClickListener{

        }

        viewmodel.homeworkLiveData.observe(this, Observer {
            myAdapter.changeData(it)
        })
        return root
    }

    override fun onItemClickListener(attachment: String) {
        Log.d(TAG, "attachment clicked")
    }

    companion object {
        private const val TAG = "TeacherHomeworkFragment"
    }
}
