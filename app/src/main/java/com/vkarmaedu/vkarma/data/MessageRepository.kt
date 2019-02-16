package com.vkarmaedu.vkarma.data

import androidx.annotation.WorkerThread

class MessageRepository(private val messageDao: MessageDao){
    val allMessages = messageDao.getAllMessages()

    @WorkerThread
    fun insert(message: Message){
        messageDao.insert(message)
    }
}