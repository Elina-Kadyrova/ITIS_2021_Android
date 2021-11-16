package com.itis.firstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.firstapp.R
import com.itis.firstapp.RabbitAdapter
import com.itis.firstapp.databinding.FragmentPetsBinding
import com.itis.firstapp.repositories.RabbitRepository


class PetsFragment: Fragment() {

    private var binding: FragmentPetsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pets,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPetsBinding.bind(view)
        with(binding){
            this!!.rvRabbits.adapter = RabbitAdapter(RabbitRepository.rabbitsList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
