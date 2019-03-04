package com.vkarmaedu.vkarma.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.vkarmaedu.vkarma.dataModels.Homework

class TeacherHomeworkViewModel : ViewModel(){
    private val databaseRef = FirebaseDatabase.getInstance().getReference("Institute/1/homework")
    private val storageRef = FirebaseStorage.getInstance().getReference("homework")
    private val homeworkList = ArrayList<Homework>()

    val homeworkLiveData : MutableLiveData<List<Homework>> = MutableLiveData()

    private val databaseListener = object : ChildEventListener{
        override fun onCancelled(p0: DatabaseError) {

        }
        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        }
        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        }

        override fun onChildAdded(p0: DataSnapshot, p1: String?) {
            val homework : Homework? = p0.getValue(Homework::class.java)
            if (homework != null){
                homeworkList.add(homework)
                homeworkLiveData.value = homeworkList

            }
        }

        override fun onChildRemoved(p0: DataSnapshot) {
        }
    }

    init {
        databaseRef.addChildEventListener(databaseListener)
    }

    override fun onCleared() {
        super.onCleared()
        databaseRef.removeEventListener(databaseListener)
    }
}