package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ClassTiming(val batch : String, val startTime : String, val endTime : String){
    constructor() : this("","",""){}
}