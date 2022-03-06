package com.example.creatuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.creatuser.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val container=supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val controller=container.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(controller)
    }
}