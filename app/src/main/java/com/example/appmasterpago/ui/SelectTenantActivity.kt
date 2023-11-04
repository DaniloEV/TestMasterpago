package com.example.appmasterpago.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.appmasterpago.R

import com.example.appmasterpago.databinding.ActivitySelectTenantBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectTenantActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectTenantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectTenantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        //Busqueme el fragment de navegación
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment?
        //En caso diferente de nulo activelo
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            NavigationUI.setupActionBarWithNavController(this, navController)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp()
    }
}