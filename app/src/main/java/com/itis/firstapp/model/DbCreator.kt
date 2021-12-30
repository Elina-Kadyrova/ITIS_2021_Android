package com.itis.firstapp.model

import android.app.Activity
import androidx.room.Room

class DbCreator {

    private fun createDBInstance(activity: Activity): TaskDb {
        return Room.databaseBuilder(activity, TaskDb::class.java, "todo_db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun initDB(activity: Activity): TaskDb {
        val db = createDBInstance(activity)
        val taskDao = db.taskDao()
        return db
    }
}
