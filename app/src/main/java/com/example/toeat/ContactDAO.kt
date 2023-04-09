package com.example.toeat

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}