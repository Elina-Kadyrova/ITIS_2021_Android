package com.itis.firstapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.itis.firstapp.MainActivity
import com.itis.firstapp.R
import com.itis.firstapp.repository.TrackRepository
import androidx.media.app.NotificationCompat.MediaStyle as NotificationCompatMediaStyle

class NotificationService(
    val context:Context)
{
    private val CHANNEL_ID = "music"
    private val notificationId = 1

    private var previousPendingIntent:PendingIntent? = null
    private var resumePendingIntent:PendingIntent? = null
    private var nextPendingIntent:PendingIntent?  = null
    private var stopPendingIntent:PendingIntent? = null
    private var playPendingIntent:PendingIntent? = null
    private var screenPendingIntent:PendingIntent? = null

    private val manager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.channel_name),
                IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.channel_description)
            }.also {
                manager.createNotificationChannel(it)
            }

            val previousIntent = Intent(context,MusicService::class.java).apply {
                action = "PREVIOUS"
            }
            val resumeIntent = Intent(context,MusicService::class.java).apply {
                action = "PAUSE"
            }
            val nextIntent = Intent(context,MusicService::class.java).apply {
                action = "NEXT"
            }
            val stopIntent = Intent(context, MusicService::class.java).apply {
                action = "STOP"
            }
            val playIntent = Intent(context,  MusicService::class.java).apply{
                action = "PLAY"
            }
           val screenIntent = Intent(context, MainActivity::class.java).apply{
               flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
           }

            previousPendingIntent = PendingIntent.getService(context,0, previousIntent,0)
            resumePendingIntent = PendingIntent.getService(context,1, resumeIntent,0)
            nextPendingIntent = PendingIntent.getService(context,2, nextIntent,0)
            stopPendingIntent = PendingIntent.getService(context, 3, stopIntent, 0)
            playPendingIntent = PendingIntent.getService(context, 4, playIntent, 0)
            screenPendingIntent = PendingIntent.getService(context, 5, screenIntent, 0)
        }
    }

    fun buildNotificationPause(id:Int){

        val track = TrackRepository.tracksList[id]

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_play_arrow_24)
            .addAction(R.drawable.ic_prev_24,"Previous", previousPendingIntent)
            .addAction(R.drawable.ic_pause_24,"Pause", resumePendingIntent)
            .addAction(R.drawable.ic_next_24,"Next", nextPendingIntent)
            .addAction(R.drawable.ic_stop_24, "Stop", stopPendingIntent)
            .setContentTitle(track.title)
            .setContentText(track.author)
            .setStyle(NotificationCompatMediaStyle())
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,track.cover))
            .setContentIntent(screenPendingIntent)

        manager.notify(notificationId, builder.build())
    }

    fun buildNotificationPlay(id:Int){
        val track = TrackRepository.tracksList[id]

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_play_arrow_24)
            .addAction(R.drawable.ic_prev_24,"Previous", previousPendingIntent)
            .addAction(R.drawable.ic_play_arrow_24, "Play", playPendingIntent)
            .addAction(R.drawable.ic_next_24,"Next", nextPendingIntent)
            .addAction(R.drawable.ic_stop_24, "Stop", stopPendingIntent)
            .setContentTitle(track.title)
            .setContentText(track.author)
            .setStyle(NotificationCompatMediaStyle())
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,track.cover))
            .setContentIntent(screenPendingIntent)

        manager.notify(notificationId, builder.build())
    }
}
