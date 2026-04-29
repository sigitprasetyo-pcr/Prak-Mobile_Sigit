package com.example.sigitapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sigitapps.Home.Pertemuan_7.SeventhActivity
import com.example.sigitapps.databinding.ActivityMainBinding
import com.example.sigitapps.Home.pertemuan_2.SecondActivity
import com.example.sigitapps.Home.pertemuan_3.ThirdActivity
import com.example.sigitapps.Home.pertemuan_4.FourthActivity
import com.example.sigitapps.Home.pertemuan_5.FifthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigasi ke Pertemuan 2
        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        // Navigasi ke Pertemuan 3
        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        // Navigasi ke Pertemuan 4
        binding.btnPertemuan4.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "User Sigit")
            startActivity(intent)
        }

        // Navigasi ke Pertemuan 5
        binding.btnPertemuan5.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }

        // Navigasi ke Pertemuan 7
        binding.btnPertemuan7.setOnClickListener {
            startActivity(Intent(this, SeventhActivity::class.java))
        }

        // Fitur Logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()
                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }
}