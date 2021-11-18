package com.itis.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.itis.firstapp.databinding.ActivityMainBinding
import com.itis.firstapp.extensions.findController

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        navController = findController(R.id.host_fragment)
        val bottomNavView = binding?.btmNav
        bottomNavView?.setupWithNavController(navController)
    }
}

