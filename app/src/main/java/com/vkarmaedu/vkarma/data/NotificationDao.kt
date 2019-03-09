package com.vkarmaedu.vkarma.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(notification: Notification)

    @Query("DELETE from notificationTable")
    fun deleteAll()

    @Delete
    fun delete(notification: Notification)

    @Query("SELECT * from notificationTable")
    fun getAllNotification() : LiveData<List<Notification>>
}