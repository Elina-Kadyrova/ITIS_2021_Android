package com.itis.firstapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import com.itis.firstapp.R

class AlarmChannel(context: Context) {

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val pattern = arrayOf(100L, 200L, 0, 400L).toLongArray()

    private val audio by lazy {
        AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()
    }

    fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                R.string.notification_channel_id_1.toString(),
                R.string.notification_channel_title.toString(),
                NotificationManager.IMPORTANCE_HIGH)
            with(channel) {
                vibrationPattern = pattern
                setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.sound_1), audio)
                description = R.string.notification_channel_desc.toString()
            }
            manager.createNotificationChannel(channel)
        }
    }
}
