package com.example.sigitapps.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sigitapps.AuthActivity
import com.example.sigitapps.Home.Pertemuan_10.TenthActivity
import com.example.sigitapps.Home.Pertemuan_7.SeventhActivity
import com.example.sigitapps.Home.Pertemuan_9.NinthActivity
import com.example.sigitapps.Home.pertemuan_2.SecondActivity
import com.example.sigitapps.Home.pertemuan_3.ThirdActivity
import com.example.sigitapps.Home.pertemuan_4.FourthActivity
import com.example.sigitapps.Home.pertemuan_5.FifthActivity
import com.example.sigitapps.Home.photo.PhotoAdapter
import com.example.sigitapps.data.api.CatFactApiClient
import com.example.sigitapps.data.api.PhotoApiClient
import com.example.sigitapps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // Load Data
        loadCatFact()
        loadPhoto()

        // Refresh Cat Fact
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        // Navigation Buttons
        binding.btnToSecond.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        binding.btnToThird.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "User Sigit")
            startActivity(intent)
        }

        binding.btnToFifth.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        binding.btnToSeventh.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        binding.btnToNinth.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        binding.btnToTenth.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    private fun loadCatFact() {
        binding.tvCatFact.text = "Loading cat fact..."
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                
                // Set LayoutManager ke Vertical (LinearLayoutManager)
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
                
                // Opsional: Untuk Grid (2 kolom) gunakan kode di bawah:
                // binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)
                
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}