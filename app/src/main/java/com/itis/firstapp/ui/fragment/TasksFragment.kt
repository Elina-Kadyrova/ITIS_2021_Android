package com.itis.firstapp.ui.fragment

import android.Manifest
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import com.itis.firstapp.databinding.AddTaskFragmentBinding
import com.itis.firstapp.databinding.TasksFragmentBinding
import com.itis.firstapp.ui.MainActivity
import com.itis.firstapp.model.TaskDb
import com.itis.firstapp.model.entities.Task
import com.itis.firstapp.ui.objects.TaskAdapter
import java.util.*

class TasksFragment : Fragment() {

    private lateinit var binding: TasksFragmentBinding
    private lateinit var taskDb: TaskDb
    private lateinit var client: FusedLocationProviderClient
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
        initRecyclerView()
        binding.addBtn.setOnClickListener {
            showEditOrAddAlertDialog(null, 0)
        }
    }

    private fun initRecyclerView() {
        val goals = taskDb.taskDao().getAll() as ArrayList<Task>
        with(binding.rvTasks) {
            adapter = TaskAdapter(goals,
                {showEditOrAddAlertDialog(it, 1)},
                {taskDb.taskDao().deleteGoal(it.id)
                    initRecyclerView()
                })
        }
        with(binding) {
            emptyTasks.visibility =
                if (taskDb.taskDao().getAll().isNotEmpty())
                    View.GONE
                else View.VISIBLE
        }
    }

    private fun showDatePicker(bindingOfEditScreen: AddTaskFragmentBinding) {
        calendar = Calendar.getInstance()
        calendar?.let { calendar ->
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

    private fun convertToTime(time: Long?): String {
        time?.let {
            val date = Date(it)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("dd.MM.yyyy HH:mm").format(date)
            } else ""
        }
        return ""
    }

    private fun showEditOrAddAlertDialog(task: Task?, editOrAdd: Int) {
        val bindingOfEditScreen = AddTaskFragmentBinding.inflate(LayoutInflater.from(context))
        var needToChangeDate = false
        val alert = context?.let {
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
            alert?.dismiss()
        }
        bindingOfEditScreen.cancelBtn.setOnClickListener {
            alert?.dismiss()
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

}
