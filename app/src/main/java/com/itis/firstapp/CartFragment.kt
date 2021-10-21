package com.itis.firstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itis.firstapp.databinding.ActivityMainBinding
import com.itis.firstapp.databinding.FragmentCartBinding

class CartFragment: Fragment(R.layout.fragment_cart) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

}
