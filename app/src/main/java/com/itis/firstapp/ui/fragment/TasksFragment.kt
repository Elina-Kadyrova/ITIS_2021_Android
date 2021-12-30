package com.itis.firstapp.ui.fragment

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.R
import com.itis.firstapp.databinding.TasksFragmentBinding
import com.itis.firstapp.ui.MainActivity
import com.itis.firstapp.model.TaskDb
import com.itis.firstapp.model.entities.Task
import com.itis.firstapp.ui.recycler_view.SpaceItemDecorator
import com.itis.firstapp.ui.recycler_view.TaskAdapter
import java.util.*

class TasksFragment : Fragment(R.layout.tasks_fragment) {

    private var binding: TasksFragmentBinding? = null
    private var taskAdapter: TaskAdapter? = null
    private lateinit var taskDb: TaskDb
    private lateinit var tasks: List<Task>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TasksFragmentBinding.bind(view)
        taskDb = (requireActivity() as MainActivity).taskDb

        taskAdapter = TaskAdapter({ showTaskFragment(it) }, { deleteTask(it) })

        binding?.apply {
            toolbar.setOnMenuItemClickListener {
                onOptionsItemSelected(it)
            }
            addBtn.setOnClickListener {
                showTaskFragment(null)
            }
            rvTasks.run {
                adapter = taskAdapter
                addItemDecoration(SpaceItemDecorator(context))
            }
        }
        updateTasks()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_tasks -> {
                deleteAllTasks()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateTasks() {
        tasks = taskDb.taskDao().getAll()
        binding?.apply {
            if (tasks.isEmpty()) {
                emptyTasks.visibility = View.VISIBLE
                rvTasks.visibility = View.GONE
            } else {
                emptyTasks.visibility = View.GONE
                rvTasks.visibility = View.VISIBLE
            }
        }
        taskAdapter?.submitList(ArrayList(tasks))
    }

    private fun deleteAllTasks() {
        if(binding?.rvTasks?.visibility == View.VISIBLE) {
            AlertDialog.Builder(requireContext())
                .setMessage("Are you sure to delete all tasks?")
                .setPositiveButton("Yes") {
                        dialog, _ ->
                    taskDb.taskDao().deleteAll()
                    updateTasks()
                    showMessage("All tasks are removed")
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") {
                        dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } else
            binding?.let {
               showMessage("You have no tasks")
            }
    }

    private fun deleteTask(id: Int) {
        taskDb.taskDao().deleteTask(id)
        updateTasks()
        showMessage("Task is done.")
    }

    private fun showTaskFragment(id: Int?) {
        var bundle: Bundle? = null
        id?.also {
            bundle = Bundle().apply {
                putInt("TASK_ID", id)
            }
        }
        findNavController().navigate(
            R.id.action_mainFragment_to_taskFragment,
            bundle
        )
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.container),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
