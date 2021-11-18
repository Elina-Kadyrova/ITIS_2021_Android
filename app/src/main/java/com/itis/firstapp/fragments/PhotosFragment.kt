package com.itis.firstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.itis.firstapp.adapters.CardAdapter
import com.itis.firstapp.R
import com.itis.firstapp.decorators.SpaceItemDecorator
import com.itis.firstapp.databinding.FragmentPhotosBinding
import com.itis.firstapp.repositories.RabbitCardRepository

class PhotosFragment: Fragment() {

    private var binding: FragmentPhotosBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotosBinding.bind(view)
        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(binding){
            this?.rvFragmentPhotos?.run {
                adapter = CardAdapter(RabbitCardRepository.cardsList)
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
