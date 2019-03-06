package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Teacher(val UId : String, val name : String, val batch : String, val age : Int,
                   val email : String, val address: String, val phoneNo : String, val subjects : Array<String>,
                   val classes : Array<ClassTiming>) {
    constructor() : this("","","",0,"","", "", emptyArray(), emptyArray())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Teacher

        if (!subjects.contentEquals(other.subjects)) return false
        if (!classes.contentEquals(other.classes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = subjects.contentHashCode()
        result = 31 * result + classes.contentHashCode()
        return result
    }

}