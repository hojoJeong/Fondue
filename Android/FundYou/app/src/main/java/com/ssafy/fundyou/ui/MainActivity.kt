package com.ssafy.fundyou.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.ActivityBridgeBinding
import com.ssafy.fundyou.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController.addOnDestinationChangedListener { _, _, _ ->
            when (navController.currentDestination?.id) {
                R.id.mainFragment -> {
                    Log.d(TAG, "initNavigation: main")
                }
                R.id.searchFragment -> {
                    Log.d(TAG, "initNavigation: search")
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity..."
    }
}