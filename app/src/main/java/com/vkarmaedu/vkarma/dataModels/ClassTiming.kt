package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ClassTiming(val standard : String, val batch : String, val startTime : String, val endTime : String){
    constructor() : this("","","",""){}
}