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

    @Query("UPDATE task SET title=:title WHERE (id=:id)")
    fun updateTitle(id: Int, title: String?)

    @Query("UPDATE task SET description=:description WHERE (id=:id)")
    fun updateDescription(id: Int, description: String?)

    @Query("UPDATE task SET date=:date WHERE (id=:id)")
    fun updateDate(id: Int, date: Date?)

    @Query("UPDATE task SET latitude=:latitude WHERE (id=:id)")
    fun updateLatitude(id: Int, latitude: Double?)

    @Query("UPDATE task SET longitude=:longitude WHERE (id=:id)")
    fun updateLongitude(id: Int, longitude: Double?)

    @Query("DELETE FROM task WHERE id=:task")
    fun deleteTask(task: Int)

    @Query("DELETE FROM task")
    fun deleteAll()
}
