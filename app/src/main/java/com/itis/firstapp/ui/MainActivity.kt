package com.itis.firstapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.itis.firstapp.R
import com.itis.firstapp.model.DbCreator
import com.itis.firstapp.model.TaskDb

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var taskDb:TaskDb
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskDb = DbCreator().initDB(this)
        controller = findController(R.id.container)
    }

    /*override fun onBackPressed() {
        when(supportFragmentManager.backStackEntryCount){
            0 -> super.onBackPressed()
            else -> supportFragmentManager.popBackStack()
        }
    }*/
}
