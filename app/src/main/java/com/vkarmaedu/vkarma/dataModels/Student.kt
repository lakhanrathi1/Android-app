package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Student(val id : String, val name : String, val batch : String) {
    constructor() : this("", "", ""){}
}