package com.vkarmaedu.vkarma.data

import androidx.annotation.WorkerThread

class NotificationRepository(private val notificationDao: NotificationDao) {
    val allNotification = notificationDao.getAllNotification()

    @WorkerThread
    fun insert(notification: Notification){
        notificationDao.insert(notification)
    }
    @WorkerThread
    fun delete(notification: Notification){
        notificationDao.delete(notification)
    }
}