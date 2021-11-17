package com.itis.firstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.R
import com.itis.firstapp.SpaceItemDecorator
import com.itis.firstapp.adapters.RabbitAdapter
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
        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding){
            this!!.rvRabbits.run {
                adapter = RabbitAdapter(RabbitRepository.rabbitsList)
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            this!!.addButton.setOnClickListener{
                val addDialogFragment = AddDialogFragment()
                val manager = parentFragmentManager
                addDialogFragment.show(manager, "addDialog")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
