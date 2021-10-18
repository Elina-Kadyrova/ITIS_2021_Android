package com.itis.firstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.firstapp.databinding.FragmentStoreBinding

class StoreFragment:Fragment(R.layout.fragment_store) {

//    private var binding: FragmentStoreBinding? = null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentStoreBinding.bind(view)
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

}
