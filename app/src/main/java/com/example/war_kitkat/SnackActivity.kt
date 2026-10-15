package com.example.war_kitkat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivitySnackBinding

class SnackActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySnackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Konfigurasi Toolbar & Tombol Back
        setSupportActionBar(binding.toolbarSnack)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_snack, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_share -> {
                Toast.makeText(this, "Bagikan KitKat ke Teman!", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_info -> {
                Toast.makeText(this, "Diproduksi oleh Nestlé", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}