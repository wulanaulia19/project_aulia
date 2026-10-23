package com.example.war_kitkat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivityAuthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol menuju alur pendaftaran email Gmail
        binding.btnRegisterGmail.setOnClickListener {
            startActivity(Intent(this, GmailInputActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.etUsername.text.toString().trim()
            val inputPassword = binding.etPassword.text.toString().trim()

            // Mengambil kredensial yang tersimpan di SharedPreferences
            val accountPref = getSharedPreferences("user_account", Context.MODE_PRIVATE)
            val savedUser = accountPref.getString("registered_username", "") ?: ""
            val savedPass = accountPref.getString("registered_password", "") ?: ""

            // Rule 1: username == password
            val isRule1Valid = inputUsername.isNotEmpty() && inputPassword.isNotEmpty() && inputUsername == inputPassword
            // Rule 2: username dan password cocok dengan data SharedPreferences
            val isRule2Valid = savedUser.isNotEmpty() && inputUsername == savedUser && inputPassword == savedPass

            if (isRule1Valid || isRule2Valid) {
                // Simpan status login
                val userPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                userPref.edit().apply {
                    putBoolean("isLogin", true)
                    putString("username", inputUsername)
                    apply()
                }

                // Arahkan ke halaman utama/BaseActivity
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                // Pesan error menggunakan MaterialAlertDialog
                MaterialAlertDialogBuilder(this)
                    .setTitle("Autentikasi Gagal")
                    .setMessage("Username atau Password salah! Pastikan kredensial Anda sesuai.")
                    .setPositiveButton("Coba Lagi") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }
}