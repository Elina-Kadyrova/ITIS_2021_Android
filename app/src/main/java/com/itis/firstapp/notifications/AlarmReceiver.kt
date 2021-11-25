package com.itis.firstapp.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.itis.firstapp.R
import com.itis.firstapp.activity.SecondActivity

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (it.action == "com.test.alarm_manager") {

                val i = Intent(context, SecondActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT)

                val notification = context?.let { cont ->
                    NotificationCompat.Builder(cont, R.string.notification_channel_id_1.toString())
                        .setSmallIcon(R.drawable.ic_alarm_on_24)
                        .setContentTitle("Wake up!")
                        .setContentText("Time is up!")
                        .setAutoCancel(true)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.sound_1))
                        .setVibrate(longArrayOf(100L, 200L, 0, 400L))
                }
                if (context != null) {
                    notification?.let {
                            not -> NotificationManagerCompat.from(context).notify(1, not.build())
                    }
                }
            }
        }
    }
}
