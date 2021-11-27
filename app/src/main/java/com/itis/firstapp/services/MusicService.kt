package com.itis.firstapp.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.itis.firstapp.models.Track
import com.itis.firstapp.repository.TrackRepository

class MusicService : Service() {
    private lateinit var mPlayer: MediaPlayer
    var currentTrackId: Int? = null
    lateinit var trackList: ArrayList<Track>
    private lateinit var musicBinder: MusicBinder;

    private lateinit var notificationController: NotificationService

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent): IBinder = musicBinder

    override fun onCreate() {
        super.onCreate()
        initFields()
        initNotificationBar()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        mPlayer.start()
        when(intent?.action){
            "PREVIOUS" -> {
                playPreviousTrack()
            }
            "RESUME" -> {
                if (mPlayer.isPlaying) pauseTrack() else playTrack()
            }
            "NEXT" -> {
                playNextTrack()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release()
    }

    private fun initFields() {
        currentTrackId = 0
        mPlayer = MediaPlayer()
        musicBinder = MusicBinder()
        trackList = TrackRepository.tracksList
    }

    private fun initNotificationBar(){

        notificationController = NotificationService(this).apply {
            build(2)
        }

    }

    fun playPreviousTrack() {
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

    fun playNextTrack() {
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
        mPlayer.pause()
    }

    fun playTrack() {
        mPlayer.start()
    }

    fun setTrack(id: Int) {
        if (mPlayer.isPlaying) mPlayer.stop()
        mPlayer = MediaPlayer.create(applicationContext, trackList[id].soundtrack)
        currentTrackId = id
        mPlayer.run {
            setOnCompletionListener {
                stop()
            }
        }
        notificationController.build(id)
    }
}
