package com.vkarmaedu.vkarma.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.dataModels.Student
import com.vkarmaedu.vkarma.viewModel.AttendanceViewModel
import kotlinx.android.synthetic.main.attendance_card.view.*
import kotlinx.android.synthetic.main.fragment_take_attendance.*

class TakeAttendanceFragment : Fragment() {

    private val TAG = javaClass.name
    private var x = 0f
    private var y = 0f
    private var x_cord = 0f
    private var y_cord = 0f
    private var currentStudent = 0

    private val windowWidth by lazy {
        val displayMetrics = DisplayMetrics()
        (activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }

    private val windowCenter by lazy { windowWidth / 2 }

    private val viewModel by lazy { ViewModelProviders.of(this).get(AttendanceViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_take_attendance, container, false)

        viewModel.getListOfAllStudents()
        viewModel.currentStudent.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                t?.let { swipeCard(it) }
            }
        })
        return root
    }

    private fun swipeCard(studentId: Int) {
        val student: Student? = viewModel.getStudent(studentId)
        Log.d(TAG, student.toString())
        if (student == null) {
            if (studentId > 0) {
                Log.d(TAG, "replace fragment")
                val bundle = Bundle()
                bundle.putParcelableArrayList("1", viewModel.getListOfAttendance())
//                findNavController().navigate(R.UId.action_teacherFragment_to_attendanceListFragment, bundle)
            }
            return
        }
        var present = 0
        val studentCard = layoutInflater.inflate(R.layout.attendance_card, teacher_card_container, false)
        studentCard.teacher_student_name.text = student.name
        studentCard.teacher_student_id.text = student.UId
        studentCard.setOnTouchListener { v, event ->

            x_cord = event.rawX
            y_cord = event.rawY
            studentCard.x = resources.getDimension(R.dimen.margin)
            studentCard.y = resources.getDimension(R.dimen.margin)

            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    x = event.x
                    y = event.y
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    x_cord = event.rawX
                    y_cord = event.rawY
                    studentCard.x = x_cord - x
                    studentCard.y = y_cord - y - resources.getDimension(R.dimen.margin)
                    studentCard.rotation = (x_cord - windowCenter) * (Math.PI / 64).toFloat()

                    if (x_cord > windowCenter) {
                        if (x_cord > windowCenter * 3 / 2) {
                            studentCard.setBackgroundColor(Color.RED)
                            present = if (x_cord > windowWidth - windowCenter / 4) 2 else 0
                        } else {
                            present = 0
                            studentCard.setBackgroundColor(Color.GRAY)
                        }
                        studentCard.setBackgroundColor(Color.GRAY)
                    } else {
                        if (x_cord < windowCenter / 2) {
                            studentCard.setBackgroundColor(Color.GREEN)
                            present = if (x_cord < windowCenter / 4) 1 else 0
                        } else {
                            present = 0
                            studentCard.setBackgroundColor(Color.GREEN)
                        }
                        studentCard.setBackgroundColor(Color.GRAY)
                    }
                    true
                }
                MotionEvent.ACTION_UP -> {
                    v?.performClick()
                    studentCard.setBackgroundColor(Color.GRAY)
                    studentCard.setBackgroundColor(Color.GRAY)

                    val message: String = when (present) {
                        1 -> {
                            viewModel.markAttendance(student, true)
                            teacher_card_container.removeView(studentCard)
                            viewModel.studentNext()
                            "present"
                        }
                        2 -> {
                            viewModel.markAttendance(student, false)
                            teacher_card_container.removeView(studentCard)
                            viewModel.studentNext()
                            "absent"
                        }
                        else -> {
                            studentCard.x = resources.getDimension(R.dimen.margin)
                            studentCard.y = resources.getDimension(R.dimen.margin)
                            studentCard.rotation = 0f
                            "nothing selected"
                        }
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        teacher_card_container.addView(studentCard)

        studentCard.attendance_left.setOnClickListener {
            teacher_card_container.removeView(studentCard)
            viewModel.studentPrev()
        }

        studentCard.attendance_right.setOnClickListener {
            teacher_card_container.removeView(studentCard)
            viewModel.studentNext()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        viewModel.currentStudent.value?.let {
            swipeCard(it)
        }
    }


}
