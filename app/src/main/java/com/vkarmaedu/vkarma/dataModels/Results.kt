package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Results(val testName : String, val subject: String, val total : Int, val obtained : Int) {
    constructor() : this("", "", 0, 0)
}