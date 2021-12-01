package com.itis.firstapp.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.itis.firstapp.models.Track
import com.itis.firstapp.repository.TrackRepository

class MusicService : Service() {
    private lateinit var mediaPlayer: MediaPlayer
    var currentTrackId: Int? = null
    lateinit var trackList: ArrayList<Track>
    private lateinit var musicBinder: MusicBinder
    private lateinit var notificationService: NotificationService

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent): IBinder = musicBinder

    override fun onCreate() {
        super.onCreate()
        currentTrackId = 0
        mediaPlayer = MediaPlayer()
        musicBinder = MusicBinder()
        trackList = TrackRepository.tracksList
        notificationService = NotificationService(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "PREVIOUS" -> {
                playPrev()
                currentTrackId?.let {
                    notificationService.buildNotificationPause(it)
                }
            }
            "PAUSE" -> {
                if (mediaPlayer.isPlaying) pauseTrack()
                currentTrackId?.let {
                    notificationService.buildNotificationPlay(it)
                }
            }
            "PLAY" ->{
                if (!mediaPlayer.isPlaying) playTrack()
                currentTrackId?.let {
                    notificationService.buildNotificationPause(it)
                }
            }
            "NEXT" -> {
                playNext()
                currentTrackId?.let {
                    notificationService.buildNotificationPause(it)
                }
            }
            "STOP" -> {
                stopTrack()
                currentTrackId?.let {
                    notificationService.buildNotificationPlay(it)
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun playPrev() {
        currentTrackId?.let {
            currentTrackId = if (it == 0) {
                trackList.size - 1
            } else {
                it - 1
            }
            setTrack(currentTrackId ?: 0)
            playTrack()
        }
    }

    fun playNext() {
        currentTrackId?.let {
            currentTrackId = if (it == trackList.size - 1) {
                0
            } else {
                it + 1
            }
            setTrack(currentTrackId ?: 0)
            playTrack()
        }
    }

    fun pauseTrack() {
        mediaPlayer.pause()
    }

    fun playTrack() {
        mediaPlayer.start()
    }

    fun stopTrack() {
        mediaPlayer.stop()
        setTrack(currentTrackId ?: 0)
    }

    fun setTrack(id: Int) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer = MediaPlayer.create(applicationContext, trackList[id].soundtrack)
        currentTrackId = id
        currentTrackId?.let {
            notificationService.buildNotificationPause(currentTrackId ?: 0)
        }
    }

    fun ismusicplaying(): Boolean{
        return mediaPlayer.isPlaying
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
