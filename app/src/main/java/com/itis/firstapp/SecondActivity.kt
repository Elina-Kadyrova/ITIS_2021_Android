package com.itis.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }

        intent?.also {
            if(intent.action == Intent.ACTION_VIEW) {
                Snackbar.make(
                    binding.root,
                    "Your geolocation is opened!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

}
