package com.example.ecommerce.ui.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.common.extensions.safeDeepLinkNavigate
import com.example.ecommerce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById((R.id.fragmentContainerView)) as NavHostFragment
        navHostFragment.navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            Log.d("BUTTON", "CLICKED")
            navController.popBackStack()
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            navController.navigate(com.example.onboarding1.R.id.nav_onboard)
//            navController.popBackStack()
//            navController.popBackStack(com.example.onboarding1.R.id.nav_onboard)
//            val navHostFragment = supportFragmentManager.findFragmentById((R.id.nav_graph))
//
//            Log.d("navHostFragment", "$navHostFragment")
//            navHostFragment?.findNavController()?.navigate(com.example.onboarding1.R.id.nav_onboard)
        }, 1000)
    }
}