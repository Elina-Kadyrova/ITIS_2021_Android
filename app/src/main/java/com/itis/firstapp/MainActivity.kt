package com.itis.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.itis.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isBtnPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding){
            btnEdit.setOnClickListener {

                if(!isBtnPressed){
                    tvUsersDescription.setVisibility(View.GONE)
                    etUsersDescription.setVisibility(View.VISIBLE)
                    isBtnPressed = true
                } else {
                    tvUsersDescription.text = etUsersDescription.text
                    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                        hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                    }
                    etUsersDescription.setVisibility(View.GONE)
                    tvUsersDescription.setVisibility(View.VISIBLE)
                    isBtnPressed = false
                }
            }
        }

    }
}
