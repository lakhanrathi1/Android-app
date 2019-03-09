package com.vkarmaedu.vkarma.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notificationTable")
data class Notification(val heading : String, val content : String, @PrimaryKey val timeStamp : Long) {}