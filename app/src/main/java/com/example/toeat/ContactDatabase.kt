package com.example.toeat

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDAO(): ContactDAO

    companion object {

        private var INSTANCE: ContactDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context : Context) : ContactDatabase? {
            if (INSTANCE == null) {
                kotlinx.coroutines.internal.synchronized(ContactDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contact"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}