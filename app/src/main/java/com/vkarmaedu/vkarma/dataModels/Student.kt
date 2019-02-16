package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Student(val id : String, val name : String, var standard : String, var batch : String) {
    constructor() : this("", "", "", ""){}
}