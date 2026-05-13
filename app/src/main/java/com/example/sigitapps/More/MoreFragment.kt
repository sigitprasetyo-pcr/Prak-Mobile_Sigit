package com.example.sigitapps.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sigitapps.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // dataList (Daftar bahasa pemrograman)
    private val dataList = listOf(
        "Kotlin", "Java", "Python", "C++", "JavaScript", "Dart", "Swift", "Go", "Ruby", "R", "PHP", "C#", "TypeScript", "Shell", "SQL", "Perl", "Rust", "Scala", "Haskell", "Lua", "Erlang", "Prolog", "Assembly", "Objective-C", "VBA"
    )

    // dataListWithDesc (Langkah 4: Untuk SimpleAdapter)
    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami"),
        mapOf("title" to "C++", "desc" to "Bahasa performa tinggi"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa pengembangan web"),
        mapOf("title" to "PHP", "desc" to "Bahasa server-side populer"),
        mapOf("title" to "C#", "desc" to "Bahasa dari Microsoft"),
        mapOf("title" to "Dart", "desc" to "Bahasa untuk Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa untuk iOS")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Setup Toolbar */
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "More"
        }

        /** 
         * Langkah 4: Menerapkan SimpleAdapter dengan layout simple_list_item_2
         * Ini menampilkan 'title' di baris pertama dan 'desc' di baris kedua.
         */
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2, // Menggunakan layout default dengan 2 teks
            arrayOf("title", "desc"),            // Key di dalam map
            intArrayOf(android.R.id.text1, android.R.id.text2) // ID TextView pada layout
        )
        
        // Hubungkan listViewItems dengan adapter
        binding.listViewItems.adapter = adapter

        // Tambahkan aksi saat item di-list diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            
            // Format Toast sesuai instruksi gambar
            Toast.makeText(
                requireContext(), 
                "Kamu memilih: $title ($desc)", 
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}