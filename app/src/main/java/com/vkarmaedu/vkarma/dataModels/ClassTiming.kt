package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ClassTiming(val subject : String, val startTime : String, val endTime : String){
    constructor() : this("","",""){}
}