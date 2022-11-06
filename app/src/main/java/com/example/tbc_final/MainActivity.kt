package com.example.tbc_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tbc_final.databinding.ActivityMainBinding
import com.example.tbc_final.utils.extensions.goAway
import com.example.tbc_final.utils.extensions.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        installSplashScreen()
        setContentView(view)
        supportActionBar?.hide()

        setUpNavigation()

    }

    private fun setUpNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val controller = findNavController(R.id.fragmentContainerView)
        navView = binding.bottomNavigation
        navView.goAway()


        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.storeFragment,
                R.id.nutritionFragment,
                R.id.storeFragment,
                R.id.favoritesFragment2
            )
        )



        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.logInFragment -> navView.goAway()
                R.id.exerciseFragment -> navView.goAway()
                R.id.calculatorFragment -> navView.show()
                R.id.homeFragment -> navView.show()
                R.id.storeFragment -> navView.show()
                R.id.BodyPartFragment -> navView.show()
                R.id.otpFragment -> navView.goAway()
                R.id.orderFragment -> navView.goAway()
                R.id.favoritesFragment2 -> navView.show()
            }
        }

        setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)
    }


}