package com.itis.firstapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.R
import com.itis.firstapp.databinding.ActivityMainBinding
import com.itis.firstapp.model.DbCreator
import com.itis.firstapp.model.TaskDb
import com.itis.firstapp.ui.fragment.TasksFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    lateinit var taskDb:TaskDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        taskDb = DbCreator().initDB(this)
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TasksFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.todo_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all_tasks) {
            if (taskDb.taskDao().getAll().isNotEmpty()) {
                AlertDialog.Builder(this)
                    .setMessage(R.string.delete_all_tasks_notif)
                    .setPositiveButton("Yes") {
                            dialog, _ ->
                        taskDb.taskDao().deleteAll()
                        initFragment()
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") {
                            dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            } else
                binding?.let {
                    Snackbar.make(
                        it.root,
                        "You have no tasks",
                        2000)
                        .show()
                }
        }
        return true
    }
}

