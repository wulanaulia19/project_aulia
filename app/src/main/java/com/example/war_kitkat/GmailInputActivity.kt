package com.example.war_kitkat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivityGmailInputBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GmailInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGmailInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGmailInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarGmail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarGmail.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.btnNextRegister.setOnClickListener {
            val email = binding.etGmailInput.text.toString().trim()

            // Validasi: tidak boleh kosong dan harus menggunakan domain @gmail.com
            when {
                email.isEmpty() -> {
                    showErrorDialog("Input Kosong", "Kolom email tidak boleh kosong!")
                }
                !email.endsWith("@gmail.com", ignoreCase = true) -> {
                    showErrorDialog("Email Tidak Valid", "Email harus menggunakan domain @gmail.com!")
                }
                else -> {
                    // Berpindah ke Halaman Registrasi sambil membawa email
                    val intent = Intent(this, RegisterActivity::class.java).apply {
                        putExtra("EXTRA_GMAIL", email)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showErrorDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Mengerti") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}