package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Student(val UId : String, val rollNo : Int, val name : String, val batch : String, val age : String,
                   val father : String, val mother : String, val email : String, val address: String, val phoneNo : String) {
    constructor() : this("", -1, "", "", "", "", "", "", "", ""){}
}