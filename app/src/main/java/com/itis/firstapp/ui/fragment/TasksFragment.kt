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
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") {
                        dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            showMessage("Все задачи успешно удалены.")
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

    /*private lateinit var binding: TasksFragmentBinding
    private lateinit var taskDb: TaskDb
    private lateinit var client: FusedLocationProviderClient
    private var spacing: SpaceItemDecorator? = null
    private var calendar: Calendar? = null
    private var longitude: Double? = null
    private var latitude: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TasksFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taskDb = (requireActivity() as MainActivity).taskDb
        spacing = SpaceItemDecorator(requireContext())
        initRecyclerView()
        binding.addBtn.setOnClickListener {
            showEditDialog(null, 0)
        }
    }

    private fun initRecyclerView() {
        val tasksList = taskDb.taskDao().getAll() as ArrayList<Task>
        with(binding) {
            rvTasks.run{
                adapter = TaskAdapter(tasksList,
                    {showEditDialog(it, 1)},
                    {taskDb.taskDao().deleteTask(it.id)
                        initRecyclerView()
                    })
                spacing?.let { addItemDecoration(it) }
            }
            emptyTasks.visibility =
                if (taskDb.taskDao().getAll().isNotEmpty())
                    View.GONE
                else View.VISIBLE
        }
    }

    private fun showDatePicker(bindingOfEditScreen: AddTaskFragmentBinding) {
        calendar = Calendar.getInstance()
        calendar?.let {
                calendar ->
           DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, day)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }
        showTimePicker(bindingOfEditScreen)
    }

    private fun showTimePicker(bindingOfEditScreen: AddTaskFragmentBinding) {

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Choose time")
            .build()

        with(picker) {
            show(this@TasksFragment.childFragmentManager, "TIME")
            addOnPositiveButtonClickListener {
                calendar?.let {
                    it[Calendar.HOUR_OF_DAY] = picker.hour
                    it[Calendar.MINUTE] = picker.minute
                    it[Calendar.SECOND] = 0
                    it[Calendar.MILLISECOND] = 0
                    bindingOfEditScreen.etTime.text = convertToTime(calendar?.time?.time)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            100 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupLocation()
                } else {
                    Toast.makeText(
                        context,
                        "Access to location denied",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertToTime(time: Long?): String {
        time?.let {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date(it))
            } else ""
        }
        return ""
    }

    private fun showEditDialog(task: Task?, editOrAdd: Int) {
        val bindingOfEditScreen = AddTaskFragmentBinding.inflate(LayoutInflater.from(context))
        var needToChangeDate = false
        val alertDialog = context?.let {
            AlertDialog.Builder(it).apply {
                setTitle(
                    if (editOrAdd == 1)
                    "Edit task"
                else "Create task")
                setView(bindingOfEditScreen.root)
            }.show()
        }
        when (editOrAdd) {
            1 -> {
                with(bindingOfEditScreen) {
                    etTitle.text = SpannableStringBuilder(task?.title)
                    etDesc.text = SpannableStringBuilder(task?.description)
                    longitude = task?.longitude
                    latitude = task?.latitude
                }
            }
        }
        bindingOfEditScreen.etTime.setOnClickListener {
            showDatePicker(bindingOfEditScreen)
            needToChangeDate = true
        }
        bindingOfEditScreen.etGeolocation.setOnClickListener {
            setupLocation()
        }
        bindingOfEditScreen.doneBtn.setOnClickListener {
            when (editOrAdd) {
                1 -> {
                    with(taskDb.taskDao()) {
                        task?.id?.let { it1 ->
                            updateTitle(it1, bindingOfEditScreen.etTitle.text.toString())
                            updateDescription(
                                it1,
                                bindingOfEditScreen.etDesc.text.toString()
                            )
                            updateDate(it1, calendar?.time)
                            updateLongitude(it1, longitude)
                            updateLatitude(it1, latitude)
                        }
                    }
                }
                else -> {
                    val newGoal = Task(
                        0,
                        bindingOfEditScreen.etTitle.text.toString(),
                        bindingOfEditScreen.etDesc.text.toString(),
                        if (needToChangeDate) calendar?.time else null,
                        longitude,
                        latitude
                    )
                    taskDb.taskDao().add(newGoal)
                }
            }
            needToChangeDate = false
            latitude = null
            longitude = null
            initRecyclerView()
            alertDialog?.dismiss()
        }
        bindingOfEditScreen.cancelBtn.setOnClickListener {
            alertDialog?.dismiss()
        }
    }

    private fun setupLocation() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_DENIED) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
            requestPermissions(permissions, 100)
        } else {
            client = LocationServices.getFusedLocationProviderClient(requireActivity())
            client.lastLocation.addOnSuccessListener { location: Location? ->
                latitude = location?.latitude
                longitude = location?.longitude
                Toast.makeText(
                    context,
                    if (location != null)
                        "Geolocation found"
                    else "Geolocation not found. Please enable it to get all the features.",
                    if (location != null)
                        Toast.LENGTH_SHORT else Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
*/
