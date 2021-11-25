package com.itis.firstapp.activity

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import com.itis.firstapp.notifications.AlarmChannel
import com.itis.firstapp.databinding.ActivityMainBinding
import com.itis.firstapp.notifications.AlarmReceiver
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker

    private var pendingIntent: PendingIntent? = null
    private var calendar: Calendar? = null
    private var alarmManager: AlarmManager? = null
    private var alarmChannel: AlarmChannel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        alarmChannel = AlarmChannel(this).also {
            it.createNotificationChannel(this)
        }

        with(binding) {
            btnSelectTime.setOnClickListener {
                showTimePicker()
            }
            btnStart.setOnClickListener {
                setAlarm()
            }
            btnStop.setOnClickListener {
                cancelAlarm()
            }
        }
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select time")
            .build()

        with(picker) {
            show(supportFragmentManager, "ALARM")
            addOnPositiveButtonClickListener {
                calendar = Calendar.getInstance().also {
                    it[Calendar.HOUR_OF_DAY] = picker.hour
                    it[Calendar.MINUTE] = picker.minute
                    it[Calendar.SECOND] = 0
                    it[Calendar.MILLISECOND] = 0
                }
                binding.tvTime.text = getTimeString()
                binding.tvTime.visibility = View.VISIBLE
            }
        }
    }

    private fun setAlarm(){
        if (calendar != null) {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            pendingIntent = saveAlarm(binding.root, calendar, alarmManager)
        } else {
            Snackbar.make(
                binding.root,
                "Time is not assigned",
                2000)
                .show()
        }
    }

    private fun saveAlarm(
        view: View,
        calendar: Calendar?,
        alarmManager: AlarmManager?,
    ): PendingIntent? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calendar?.timeInMillis?.let {
                val intent = Intent(applicationContext, AlarmReceiver::class.java).apply {
                    action = "com.test.alarm_manager"
                }
                val pendingIntent =
                    PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager?.setExact(AlarmManager.RTC_WAKEUP, it, pendingIntent)
                Snackbar.make(
                    view, "The alarm will ring at the specified time", 2000)
                    .show()
                return pendingIntent
            }
        }
        return null
    }

    private fun cancelAlarm(){
        if (calendar != null) {
            AlertDialog.Builder(this@MainActivity).apply {
                setTitle("Delete alarm")
                setMessage("Are you sure to delete alarm?")
                setPositiveButton("OK") {
                        dialog, _ ->
                        alarmManager?.cancel(pendingIntent)
                        alarmManager = null
                        binding.tvTime.visibility = View.INVISIBLE
                        calendar = null
                        dialog.cancel()
                }
                setNegativeButton("Cancel") {
                        dialog, _ ->
                        dialog.cancel()
                }
            }.show()

        } else {
            Snackbar.make(
                binding.root,
                "Time is not assigned",
                2000)
                .show()
        }
    }

    private fun getTimeString(): String? {
        calendar?.let {
            val hours =
                if (it[Calendar.HOUR_OF_DAY] < 10) {
                    "0${it[Calendar.HOUR_OF_DAY]}"
                }
                else "${it[Calendar.HOUR_OF_DAY]}"
            val minutes =
                if (it[Calendar.MINUTE] < 10) {
                    "0${it[Calendar.MINUTE]}"
                }
                else "${it[Calendar.MINUTE]}"
            return "$hours:$minutes"
        }
        return null
    }
}
