package com.itis.firstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.R
import com.itis.firstapp.SwipeToDeleteCallback
import com.itis.firstapp.decorators.SpaceItemDecorator
import com.itis.firstapp.adapters.RabbitAdapter
import com.itis.firstapp.databinding.FragmentPetsBinding
import com.itis.firstapp.models.Rabbit
import com.itis.firstapp.repositories.RabbitRepository

class PetsFragment: Fragment() {

    private var binding: FragmentPetsBinding? = null
    private var rabbitAdapter: RabbitAdapter? = null

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
        rabbitAdapter = RabbitAdapter({ deleteRabbit(it) })
        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding){
            this?.rvRabbits?.run {
                adapter = rabbitAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            this?.addButton?.setOnClickListener{
                AddDialogFragment.show(childFragmentManager, {addRabbit(it)}, {nullData(it) })
            }
            val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val pos = viewHolder.adapterPosition
                    RabbitRepository.rabbitsList.removeAt(pos)
                    rabbitAdapter?.submitList(RabbitRepository.rabbitsList)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
            itemTouchHelper.attachToRecyclerView(this?.rvRabbits)
        }
        rabbitAdapter?.submitList(RabbitRepository.rabbitsList)
    }

    private fun deleteRabbit(rabbit: Rabbit){
        RabbitRepository.removeRabbit(rabbit)
        rabbitAdapter?.submitList(RabbitRepository.rabbitsList)
    }

    private fun addRabbit(array: Array<String>){
        val name = array[0]
        val breed = array[1]
        val position: Int = if (array[2].isEmpty()) {
            -1
        } else {
            array[2].toInt()
        }
        val rabbit = Rabbit(RabbitRepository.rabbitsList.size, name, breed)
        RabbitRepository.addRabbit(position, rabbit)
        rabbitAdapter?.submitList(RabbitRepository.rabbitsList)
    }

    private fun nullData(flag: Boolean) {
        if (!flag) {
            Snackbar.make(
                requireActivity().findViewById(R.id.host_fragment),
                "Введите полные данные",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
