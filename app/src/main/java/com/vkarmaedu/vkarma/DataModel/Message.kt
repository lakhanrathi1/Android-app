package com.vkarmaedu.vkarma.DataModel

data class Message(val name : String, val id : String, val text : String) {
    constructor() : this("", "", "")
}