package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Homework (val subName : String, val teachName : String, val batch: String, val date : Date, val text : String, val attachment : String? ){
    constructor() : this("", "","", Date(), "", null)
}