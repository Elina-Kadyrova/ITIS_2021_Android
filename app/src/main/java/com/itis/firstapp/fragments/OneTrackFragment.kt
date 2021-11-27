package com.itis.firstapp.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.itis.firstapp.R
import com.itis.firstapp.models.Track
import com.itis.firstapp.repository.TrackRepository
import com.itis.firstapp.services.MusicService

class OneTrackFragment : Fragment(R.layout.fragment_track_one) {
    private var binding: OneTrackFragment? = null

    private lateinit var mTrack: Track
    private lateinit var titleView: TextView
    private lateinit var authorView: TextView
    private lateinit var coverView: ImageView

    private lateinit var previousButton: ImageView
    private lateinit var playButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var pauseButton:ImageView

    private var musicService: MusicService? = null

    private val binderConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            musicService = (service as? MusicService.MusicBinder)?.getService()
            if(musicService != null){
                initView()
            }
        }

        override fun onServiceDisconnected(className: ComponentName) {
            musicService = null
        }
    }

    override fun onResume() {
        super.onResume()
        initService()
    }

    private fun initService(){
        val intent = Intent(this.context, MusicService::class.java)
        activity?.bindService(intent, binderConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
    }

    private fun initFields() {
            previousButton = requireActivity().findViewById(R.id.nav_prev)
            playButton = requireActivity().findViewById(R.id.nav_play)
            nextButton = requireActivity().findViewById(R.id.nav_next)
            pauseButton = requireActivity().findViewById(R.id.nav_pause)
    }

    private fun initView() {
        val id = arguments?.getInt("id")
        id?.let {
            mTrack = TrackRepository.tracksList[id]

            titleView.text = mTrack.title
            authorView.text = mTrack.author
            coverView.setImageResource(mTrack.cover)
            initMusicNavigationView(id)
        }
    }

    private fun initMusicNavigationView(id: Int) {
        musicService?.setTrack(id)
        musicService?.playTrack()

        playButton.setOnClickListener {
            musicService?.playTrack()
            showPauseSign()
        }
        previousButton.setOnClickListener {
            musicService?.playPreviousTrack()
            updateView(musicService?.currentTrackId?:0)
        }
        nextButton.setOnClickListener {
            musicService?.playNextTrack()
            updateView(musicService?.currentTrackId?:0)
        }
        pauseButton.setOnClickListener {
            musicService?.pauseTrack()
            showPlaySign()
        }
    }

    private fun updateView(id:Int){
        id.let {
            mTrack = TrackRepository.tracksList[id]

            titleView.text = mTrack.title
            authorView.text = mTrack.author
            coverView.setImageResource(mTrack.cover)

            showPauseSign()

            playButton.setOnClickListener {
                musicService?.playTrack()
                showPauseSign()
            }

        }
    }

    private fun showPauseSign(){
        playButton.visibility = View.GONE
        pauseButton.visibility = View.VISIBLE
    }

    private fun showPlaySign(){
        playButton.visibility = View.VISIBLE
        pauseButton.visibility = View.GONE
    }
}
