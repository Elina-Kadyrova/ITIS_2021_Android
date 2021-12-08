package com.itis.firstapp.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.firstapp.R
import com.itis.firstapp.databinding.FragmentTrackOneBinding
import com.itis.firstapp.repository.TrackRepository
import com.itis.firstapp.services.MusicService

class OneTrackFragment : Fragment(R.layout.fragment_track_one) {

    private var binding: FragmentTrackOneBinding? = null
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
        val intent = Intent(this.context, MusicService::class.java)
        activity?.bindService(intent, binderConnection, Context.BIND_AUTO_CREATE)
        musicService?.currentTrackId?.let {
            updateView(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrackOneBinding.bind(view)
        initView()
    }

    private fun initView() {
        arguments?.getInt("id")?.let { id ->
            val currentTrack = TrackRepository.getTrackById(id)

            with(binding) {
                this?.tvTrackTitle?.text = currentTrack.title
                this?.tvTrackAuthor?.text = currentTrack.author
                this?.trackCover?.setImageResource(currentTrack.cover)
            }
            initMusic(id)
        }
    }

    private fun initMusic(id: Int) {
        musicService?.setTrack(id)
        musicService?.playTrack()

        with(binding){
            this?.navPlay?.setOnClickListener {
                musicService?.playTrack()
                showPauseSign()
            }
            this?.navPrev?.setOnClickListener {
                musicService?.playPrev()
                updateView(musicService?.currentTrackId?:0)
            }
            this?.navNext?.setOnClickListener {
                musicService?.playNext()
                updateView(musicService?.currentTrackId?:0)
            }
            this?.navPause?.setOnClickListener {
                musicService?.pauseTrack()
                showPlaySign()
            }
            this?.navStop?.setOnClickListener {
                musicService?.stopTrack()
                showPlaySign()
            }
        }
    }

    private fun updateView(id:Int){
        val currentTrack = TrackRepository.getTrackById(id)

        with(binding) {
            this?.tvTrackTitle?.text = currentTrack.title
            this?.tvTrackAuthor?.text = currentTrack.author
            this?.trackCover?.setImageResource(currentTrack.cover)
            this?.navPlay?.setOnClickListener {
                musicService?.playTrack()
                showPauseSign()
            }
        }

        if(musicService?.ismusicplaying() == true){
            showPauseSign()

        } else {
            showPlaySign()
        }
    }

    private fun showPauseSign(){
        with(binding){
            this?.navPlay?.visibility = View.GONE
            this?.navPause?.visibility = View.VISIBLE
        }
    }

    private fun showPlaySign(){
        with(binding){
            this?.navPlay?.visibility = View.VISIBLE
            this?.navPause?.visibility = View.GONE
        }
    }
}
