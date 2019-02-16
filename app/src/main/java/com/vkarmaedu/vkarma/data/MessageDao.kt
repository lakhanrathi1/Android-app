package com.vkarmaedu.vkarma.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(message: Message)

    @Query("DELETE from messageTable")
    fun deleteAll()

    @Query("SELECT * from messageTable")
    fun getAllMessages() : LiveData<List<Message>>
}