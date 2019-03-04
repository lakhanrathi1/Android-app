package com.vkarmaedu.vkarma.dataModels

data class Homework(val subject : String, val batch : String, val teacher : String, val text : String,
                    val attachment : String?, val timeStamp : Long) {
    constructor() : this("", "", "", "", null, 0L)
}