package com.example.pract4

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DateListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = DateListAdapter(loadDates())
        recyclerView.adapter = adapter
    }

    private fun loadDates(): List<String> {
        val photosDir =
            File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos")
        val dateFile = File(photosDir, "date.txt")
        val data = mutableListOf<String>()

        try {
            FileInputStream(dateFile).use { inputStream ->
                InputStreamReader(inputStream).use { reader ->
                    reader.forEachLine { line ->
                        data.add(line)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return data
    }
}