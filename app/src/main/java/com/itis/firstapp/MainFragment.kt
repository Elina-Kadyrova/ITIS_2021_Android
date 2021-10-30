package com.itis.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainFragment: Fragment(R.layout.fragment_main) {

    private var rabbitAdapter: RabbitAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rabbitAdapter = RabbitAdapter(RabbitRepository.rabbits) {
            showSelectedTitle(it)
        }
        view.findViewById<RecyclerView>(R.id.rv_rabbits).run {
            adapter = rabbitAdapter
        }
    }

    private fun showSelectedTitle(name: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "Name: $name",
            Snackbar.LENGTH_LONG
        ).show()
    }
}

