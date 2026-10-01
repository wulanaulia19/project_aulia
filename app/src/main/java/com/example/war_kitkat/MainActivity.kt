package com.example.war_kitkat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Event klik tombol "My Project" memicu Intent ke SnackDetailActivity
        binding.btnMyProject.setOnClickListener {
            val intent = Intent(this, SnackActivity::class.java)
            startActivity(intent)
        }
    }
}