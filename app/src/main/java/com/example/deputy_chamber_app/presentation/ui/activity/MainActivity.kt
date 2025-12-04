package com.example.deputy_chamber_app.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deputy_chamber_app.R
import com.example.deputy_chamber_app.databinding.ActivityMainBinding
import com.example.deputy_chamber_app.presentation.ui.activity.fragment.DeputyListFragment
import com.example.deputy_chamber_app.presentation.ui.activity.fragment.PartyListFragment

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
            val currentFragment = supportFragmentManager.findFragmentById(R.id.frameLayout)

            when (item.itemId) {
                R.id.deputies -> {
                    if (currentFragment !is DeputyListFragment) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, DeputyListFragment())
                            .commit()
                    }
                    true
                }
                R.id.podium -> {
                    if (currentFragment !is PartyListFragment) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, PartyListFragment())
                            .commit()
                    }
                    true
                }
                R.id.file -> {
//                    if (currentFragment !is DeputyFileFragment) {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.frameLayout, DeputyFileFragment())
//                            .commit()
//                    }
                    true
                }
                else -> false
            }
        }

    }
}