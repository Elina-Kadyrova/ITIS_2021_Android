package com.itis.firstapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.itis.firstapp.databinding.ActivityMainBinding

private const val REQUEST_CODE_EMAIL = 1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding) {

            btnSendEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("kad_elina@mail.ru"))
                    putExtra(Intent.EXTRA_SUBJECT, "Hello")
                    putExtra(Intent.EXTRA_TEXT, "World")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivityForResult(intent, REQUEST_CODE_EMAIL)
                }
            }

            btnToSecondActivity.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        SecondActivity::class.java
                    )
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var message = ""
        if (resultCode == Activity.RESULT_OK) { //Email on my phone always returned "Result canceled" even if email was sent, I don't know why(
            when(requestCode){
                REQUEST_CODE_EMAIL -> message = "Email was sent!"
                else -> super.onActivityResult(requestCode, resultCode, data)
            }
        }
        else {
            message = "Some problems..."
            super.onActivityResult(requestCode, resultCode, data)
        }
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
