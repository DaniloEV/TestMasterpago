package com.example.appmasterpago.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.appmasterpago.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
///Cuan
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

//        Handler().postDelayed({
//            val intent= Intent(this@MainActivity, FullscreenActivity::class.java)
//            startActivity(intent)
//            finish()
//        },3000)
        Handler().postDelayed({
            val intent= Intent(this@MainActivity, SelectTenantActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}