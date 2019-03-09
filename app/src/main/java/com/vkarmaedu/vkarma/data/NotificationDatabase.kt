package com.vkarmaedu.vkarma.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notification::class], version = 1, exportSchema = false)
abstract class NotificationDatabase : RoomDatabase() {

    abstract fun notificationDao(): NotificationDao

    companion object {
        @Volatile
        private var INSTANCE: NotificationDatabase? = null

        fun getDatabase(context: Context): NotificationDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    if (INSTANCE != null) {
                        return INSTANCE as NotificationDatabase
                    }
                    val newInstance =
                        Room.databaseBuilder(context.applicationContext, NotificationDatabase::class.java, "notificationDatabase")
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = newInstance
                    return newInstance
                }
            }
        }
    }
}