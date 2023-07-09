package com.example.presentz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.presentz.R
import com.example.presentz.adapters.ProductAdapter
import com.example.presentz.databinding.ActivityMainBinding
import com.example.presentz.models.ProductDetails
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    var i=0;
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController=navHostFragment.navController
        val bottomNavigationView:BottomNavigationView=findViewById(R.id.nav_view)
        setupWithNavController(bottomNavigationView,navController)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {
                    i=0
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.cartFragment -> {
                   i=1
                    navController.navigate(R.id.cartFragment)
                    true
                }
                R.id.giftFragment -> {
                    i=2
                    navController.navigate(R.id.giftFragment)
                    true
                }
                R.id.accountFragment->{
                    i=3
                    navController.navigate(R.id.accountFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(i==0)
            finish()

    }
}