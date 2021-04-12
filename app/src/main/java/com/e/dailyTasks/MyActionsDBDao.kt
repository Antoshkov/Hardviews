package com.e.dailyTasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyActionsDBDao {
    @get:Query("SELECT * FROM `Action`")
    val all: LiveData<List<Action>>

    @Query("SELECT * FROM `Action` WHERE id = :id")
    fun getById(id: Int): Action?

    @Insert
    fun insert(actionsList: List<Action?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(action: Action?)

    @Update
    fun update(action: Action?)

    @Delete
    fun delete(action: Action?)

    @Query("DELETE FROM `Action`")
    fun deleteAll()
}