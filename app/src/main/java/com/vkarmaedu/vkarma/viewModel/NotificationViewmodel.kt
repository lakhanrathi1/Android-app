package com.vkarmaedu.vkarma.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vkarmaedu.vkarma.data.Notification
import com.vkarmaedu.vkarma.data.NotificationDatabase
import com.vkarmaedu.vkarma.data.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class NotificationViewmodel(application: Application) : AndroidViewModel(application) {
    val allNotification : LiveData<List<Notification>>
    private val notificationRepo : NotificationRepository
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    init {
        val dao = NotificationDatabase.getDatabase(application).notificationDao()
        notificationRepo = NotificationRepository(dao)
        allNotification = notificationRepo.allNotification
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun delete(notification: Notification){
        notificationRepo.delete(notification)
    }

    companion object {
        private const val TAG = "NotificationViewmodel"
    }
}