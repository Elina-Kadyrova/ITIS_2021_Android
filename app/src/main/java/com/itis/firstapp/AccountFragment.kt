package com.itis.firstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.firstapp.databinding.FragmentAccountBinding

class AccountFragment: Fragment(R.layout.fragment_account) {

//    private var binding: FragmentAccountBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? = FragmentAccountBinding.inflate(inflater, container, false)?.let {
//        binding = it
//        it.root
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
        return inflater.inflate(R.layout.fragment_account, container, false)
    }
}
