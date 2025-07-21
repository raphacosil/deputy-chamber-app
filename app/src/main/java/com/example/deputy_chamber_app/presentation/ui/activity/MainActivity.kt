package com.example.deputy_chamber_app.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deputy_chamber_app.R
import com.example.deputy_chamber_app.databinding.ActivityMainBinding
import com.example.deputy_chamber_app.presentation.ui.activity.fragment.DeputyListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val defaultFragment = DeputyListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, defaultFragment)
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.deputies -> {
                    val deputyListFragment = DeputyListFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, deputyListFragment)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}