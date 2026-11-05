package com.example.war_kitkat

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivitySnackBinding // sesuaikan nama binding activity-mu
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SnackActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySnackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hubungkan Toolbar sebagai ActionBar
        setSupportActionBar(binding.toolbarSnack) // pastikan id toolbar di XML-mu adalah toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "KitKat Break Lounge"
    }

    // 1. Inflate menu_snack.xml
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_snack, menu)
        return true
    }

    // 2. Tangani klik menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_share -> {
                shareKitKatProduct()
                true
            }
            R.id.action_info -> {
                showInfoDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Fungsi Share via Android Sharesheet
    private fun shareKitKatProduct() {
        val shareText = """
            🍫 KitKat 4 Fingers Bar - Break Lounge
            Kombinasi klasik empat bilah wafer renyah berlapis cokelat susu berkualitas.
            
            Informasi Nutrisi:
            • Kalori Total: 210 Kkal
            • Lemak Nabati: 11 gram
            • Karbohidrat: 26 gram
            • Protein Susu: 3.2 gram
            
            Yuk nikmati waktu istirahatmu dengan KitKat Break Lounge di aplikasi WAR KitKat!
        """.trimIndent()

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(shareIntent, "Bagikan KitKat Lounge via:"))
    }

    private fun showInfoDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Tentang KitKat Lounge")
            .setMessage("KitKat Break Lounge menyajikan katalog camilan KitKat pilihan beserta detail kandungan nutrisi untuk menemani waktu santaimu.")
            .setPositiveButton("Tutup") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}