package com.example.war_kitkat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivitySnackBinding

class SnackActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySnackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}