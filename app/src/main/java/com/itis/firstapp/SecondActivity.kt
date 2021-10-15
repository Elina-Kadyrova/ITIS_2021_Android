package com.itis.firstapp

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var butMap: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }

        intent?.also {
            print("hiii")
        }
    }

//        Snackbar.make(
//            binding.root,
//            "Opened",
//            Snackbar.LENGTH_LONG
//        ).show()
//    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            binding.tvInfo.text = it
        }
    }

}
