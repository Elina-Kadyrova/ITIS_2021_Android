package com.itis.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.itis.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, StoreFragment())
            .add(R.id.fragment_container, CartFragment())
            .add(R.id.fragment_container, FavoritesFragment())
            .add(R.id.fragment_container, AccountFragment())
            .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
            .commit()

        supportFragmentManager.popBackStack() //вернемся на 1 шаг назад

        binding?.run{

            ibStore.setOnClickListener{
                replaceFragment(StoreFragment())
            }
            ibCart.setOnClickListener{
                replaceFragment(CartFragment())
            }
            ibFavorites.setOnClickListener{
                replaceFragment(FavoritesFragment())
            }
            ibAccount.setOnClickListener{
               replaceFragment(AccountFragment())
            }
        }

    }

    private fun replaceFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
