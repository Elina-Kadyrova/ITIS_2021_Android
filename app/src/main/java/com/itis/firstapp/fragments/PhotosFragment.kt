package com.itis.firstapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.firstapp.CardAdapter
import com.itis.firstapp.R
import com.itis.firstapp.databinding.FragmentPhotosBinding
import com.itis.firstapp.repositories.RabbitCardRepository

class PhotosFragment: Fragment() {

    private var binding: FragmentPhotosBinding? = null
    var adapter : CardAdapter? = null

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
        with(binding){
            adapter = CardAdapter(RabbitCardRepository.cardsList)
            this!!.rvFragmentPhotos.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
