package com.itis.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.itis.firstapp.databinding.ActivityMainBinding

private const val TAG_STORE = "TAG_STORE"
private const val TAG_CART = "TAG_CART"
private const val TAG_ACCOUNT = "TAG_ACCOUNT"
private const val TAG_FAVORITES = "TAG_FAVORITES"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .add(R.id.fragment_container, StoreFragment(), TAG_STORE)
            .commit()

        with(binding){

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
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
