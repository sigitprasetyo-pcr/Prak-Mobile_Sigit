package com.example.sigitapps.pertemuan_5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.sigitapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    // Variabel penanda agar tidak lag saat scroll
    private var isAppBarVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengaktifkan toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://merdeka.com")

        // Agar Toolbar hide/show saat scroll web dengan optimasi anti-lag
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY && isAppBarVisible) {
                binding.appBar.setExpanded(false, true) // sembunyikan
                isAppBarVisible = false
            } else if (scrollY < oldScrollY && !isAppBarVisible) {
                binding.appBar.setExpanded(true, true) // tampilkan
                isAppBarVisible = true
            }
        }
    }

    // Mengaktifkan tombol back pada toolbar
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Mengontrol navigasi back pada WebView
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack() // Kembali ke halaman web sebelumnya
        } else {
            super.onBackPressed() // Keluar dari activity
        }
    }
}