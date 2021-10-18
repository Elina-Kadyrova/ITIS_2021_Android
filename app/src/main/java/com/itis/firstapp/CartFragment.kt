package com.itis.firstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.firstapp.databinding.FragmentCartBinding

class CartFragment: Fragment(R.layout.fragment_cart) {

//    private var binding: FragmentCartBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? = FragmentCartBinding.inflate(inflater, container, false)?.let {
//        binding = it
//        it.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding?.run {
//
//        }
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
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
}
