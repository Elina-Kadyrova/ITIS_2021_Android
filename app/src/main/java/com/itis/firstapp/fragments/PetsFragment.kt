package com.itis.firstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.R
import com.itis.firstapp.SwipeToDeleteCallback
import com.itis.firstapp.decorators.SpaceItemDecorator
import com.itis.firstapp.adapters.RabbitAdapter
import com.itis.firstapp.databinding.FragmentPetsBinding
import com.itis.firstapp.models.Rabbit
import com.itis.firstapp.repositories.RabbitRepository


class PetsFragment: Fragment() {

    private var binding: FragmentPetsBinding? = null
    private var adapter: RabbitAdapter? = null

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
                adapter = RabbitAdapter(RabbitRepository.rabbitsList) { deleteRabbit(it) }
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
            this!!.addButton.setOnClickListener{
                val addDialogFragment = AddDialogFragment()
                addDialogFragment.show(parentFragmentManager, "addDialog")
            }

            val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val pos = viewHolder.adapterPosition
                    RabbitRepository.rabbitsList.removeAt(pos)
                    adapter?.notifyItemRemoved(pos)
                }
            }

            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
            itemTouchHelper.attachToRecyclerView(this!!.rvRabbits)

            adapter?.submitList(RabbitRepository.rabbitsList)
        }
    }

    private fun deleteRabbit(rabbit: Rabbit){
        RabbitRepository.removeRabbit(rabbit)
        adapter?.submitList(RabbitRepository.rabbitsList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
