package com.vkarmaedu.vkarma.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.vkarmaedu.vkarma.dataModels.Attendance
import com.vkarmaedu.vkarma.dataModels.Student
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AttendanceViewModel : ViewModel() {

    private val TAG = javaClass.name
    private val listStudent = ArrayList<Student>()
    private val listAttendance = ArrayList<Attendance>()
    val currentStudent : MutableLiveData<Int> = MutableLiveData(-1)

    private val dateFormat = SimpleDateFormat.getDateInstance()
    private val today = dateFormat.format(Date())

    private val attendanceRef by lazy {
        FirebaseDatabase.getInstance().getReference("Institute/1/Attendance/$today")
    }
    private val studentRef: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("Institute/1/Students")
    }


    fun getListOfAllStudents() {
        studentRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(snap: DatabaseError) {
            }

            override fun onDataChange(snap: DataSnapshot) {
                for (child in snap.children) {
                    try {
                        val student = child.getValue(Student::class.java)
                        student?.let { listStudent.add(it) }
                        Log.d(TAG, student.toString())
                    } catch (e: Exception) {
                        Log.d(TAG, e.message)
                    }
                }
                currentStudent.value = 0
            }
        })
    }

    fun getListOfAttendance() : ArrayList<Attendance> {
        Log.d(TAG, listAttendance.toString())
        return listAttendance
    }

    fun getStudent(id: Int) : Student?{
        return listStudent.getOrNull(id)
    }

    fun studentNext(){
        currentStudent.value = currentStudent.value?.plus(1)
    }

    fun studentPrev(){
        currentStudent.value = currentStudent.value?.minus(1)
    }

    fun markAttendance(student: Student, attendance: Boolean){
        val atten = Attendance(student.id, student.name, attendance)
        attendanceRef.child(student.id).setValue(atten)
        listAttendance.add(atten)
    }
}