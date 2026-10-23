package com.example.war_kitkat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.war_kitkat.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar dengan tombol back
        setSupportActionBar(binding.toolbarReg)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarReg.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val gmailExtra = intent.getStringExtra("EXTRA_GMAIL") ?: ""
        binding.etRegEmail.setText(gmailExtra)

        // Set nama default
        if (binding.etRegNama.text.isNullOrEmpty()) {
            binding.etRegNama.setText("Wulan Aulia")
        }

        binding.btnSubmitRegister.setOnClickListener {
            val nama = binding.etRegNama.text.toString().trim()
            val email = binding.etRegEmail.text.toString().trim()
            val username = binding.etRegUsername.text.toString().trim()
            val password = binding.etRegPassword.text.toString().trim()

            // 1. Validasi: Semua field wajib diisi
            if (nama.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showFailedDialog("Registrasi Gagal", "Semua kolom inputan wajib diisi!")
                return@setOnClickListener
            }

            // 2. Validasi: Username tidak boleh mengandung spasi
            if (username.contains(" ")) {
                showFailedDialog("Registrasi Gagal", "Username tidak boleh mengandung karakter spasi!")
                return@setOnClickListener
            }

            // 3. Validasi: Password minimal 6 karakter
            if (password.length < 6) {
                showFailedDialog("Registrasi Gagal", "Password harus memiliki panjang minimal 6 karakter!")
                return@setOnClickListener
            }

            // Simpan seluruh data akun ke SharedPreferences
            val accountPref = getSharedPreferences("user_account", Context.MODE_PRIVATE)
            accountPref.edit().apply {
                putString("registered_name", nama)
                putString("registered_email", email)
                putString("registered_username", username)
                putString("registered_password", password)
                apply()
            }

            // Tampilkan dialog berhasil sebelum berpindah ke halaman success
            MaterialAlertDialogBuilder(this)
                .setTitle("Registrasi Berhasil")
                .setMessage("Selamat, akun atas nama $nama berhasil didaftarkan!")
                .setCancelable(false)
                .setPositiveButton("Lanjut") { dialog, _ ->
                    dialog.dismiss()
                    val intent = Intent(this, RegisterSuccessActivity::class.java).apply {
                        putExtra("REGISTERED_NAME", nama)
                        putExtra("REGISTERED_EMAIL", email)
                    }
                    startActivity(intent)
                    finish()
                }
                .show()
        }
    }

    private fun showFailedDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Perbaiki") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}