package com.itis.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val juice = Juice("Добрый",116.0, 365, 1000)
        juice.beDrunk()
        juice.bePoured()
        juice.beSpilled()
        println(juice.name)
        println("The volume is ${juice.volume}")
        val biscuit = Biscuit("Овсяное печенье", 67.5, 365, 250)
        biscuit.beBittenOff()
        biscuit.beEaten()
        println("The price is ${biscuit.price}")
        biscuit.price = 57.9
        println("The price is ${biscuit.price}")
    }
}
