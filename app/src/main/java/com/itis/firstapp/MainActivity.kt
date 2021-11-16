package com.itis.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.itis.firstapp.databinding.ActivityMainBinding

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
        //bottomNavView?.setupWithNavController(navController)

       with(binding) {
            this?.btmNav?.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        onHomeClick()
                        true
                    }
                    R.id.menu_pets -> {
                        onPetsClick()
                        true
                    }
                    R.id.menu_photos -> {
                        onPhotosClick()
                        true
                    }
                    else -> false
                }
            }
       }
    }

    private fun onHomeClick(){
        val options = navOptions {
            launchSingleTop = false
            anim {
                enter = R.anim.enter_from_right
                exit = R.anim.exit_to_left
                popEnter = R.anim.enter_from_left
                popExit = R.anim.exit_to_right
            }
        }
        navController.navigate(R.id.homeFragment, null, options)
    }

    private fun onPetsClick(){
        val options = navOptions {
            launchSingleTop = false
            anim {
                enter = R.anim.enter_from_right
                exit = R.anim.exit_to_left
                popEnter = R.anim.enter_from_left
                popExit = R.anim.exit_to_right
            }
        }
        navController.navigate(R.id.petsFragment, null, options)
    }

    private fun onPhotosClick(){
        val options = navOptions {
            launchSingleTop = false
            anim {
                enter = R.anim.enter_from_right
                exit = R.anim.exit_to_left
                popEnter = R.anim.enter_from_left
                popExit = R.anim.exit_to_right
            }
        }
        navController.navigate(R.id.photosFragment, null, options)
    }
}

