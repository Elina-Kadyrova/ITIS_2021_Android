package com.itis.firstapp.model.dao

import androidx.room.*
import com.itis.firstapp.model.entities.Task
import java.util.*

@Dao
interface TaskDao {

    @Insert
    fun add(task: Task)

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: Int): Task?

    @Update
    fun updateTask(task: Task)

    @Query("DELETE FROM task WHERE id=:task")
    fun deleteTask(task: Int)

    @Query("DELETE FROM task")
    fun deleteAll()
}
