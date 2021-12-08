package com.itis.firstapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.R
import com.itis.firstapp.databinding.FragmentTrackListBinding
import com.itis.firstapp.repository.TrackRepository
import com.itis.firstapp.rv.TrackAdapter

class TrackListFragment : Fragment(R.layout.fragment_track_list) {

    private var binding: FragmentTrackListBinding? = null
    private var trackAdapter: TrackAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTrackListBinding.bind(view)
        trackAdapter = TrackAdapter(TrackRepository.tracksList){
            showOneTrackFragment(it)
        }

        val itemDecoration: RecyclerView.ItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        with(binding){
            this?.rvTracks?.addItemDecoration(itemDecoration)
            this?.rvTracks?.adapter = trackAdapter
        }
    }

    private fun showOneTrackFragment(id: Int) {
        val bundle = Bundle().apply {
            putInt("id", id)
        }
        val options = NavOptions.Builder()
            .setLaunchSingleTop(false)
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()
        findNavController().navigate(R.id.action_trackListFragment_to_trackDetailFragment, bundle, options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
