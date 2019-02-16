package com.vkarmaedu.vkarma.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "messageTable")
data class Message(val userName : String, val text: String, @PrimaryKey val timeStamp: Date?, val attachment : String?) {
    constructor() : this( "", "", null, null)
}
