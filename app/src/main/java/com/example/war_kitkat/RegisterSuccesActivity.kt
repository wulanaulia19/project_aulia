package com.example.war_kitkat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivityRegisterSuccesBinding

class RegisterSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSuccesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSuccesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("REGISTERED_NAME") ?: "Pengguna"
        val email = intent.getStringExtra("REGISTERED_EMAIL") ?: ""
        binding.tvSuccessDetails.text = "Selamat datang, $name!\nAkun Anda dengan email $email telah berhasil terdaftar dan siap digunakan."

        binding.btnBackToLogin.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}