package com.vkarmaedu.vkarma.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.vkarmaedu.vkarma.dataModels.Homework

class StudentHomeworkViewModel : ViewModel() {
    var listHomework : MutableLiveData<List<Homework>> = MutableLiveData()
    val list = ArrayList<Homework>()

    val databaseRef = FirebaseDatabase.getInstance().getReference("Institute/1/")

    init {

    }
}