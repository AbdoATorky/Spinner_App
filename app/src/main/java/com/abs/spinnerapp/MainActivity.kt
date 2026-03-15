package com.abs.spinnerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupSpinner()

    }

    private fun setupSpinner(){
        val spinner = findViewById<Spinner>(R.id.anime_list)
        ArrayAdapter.createFromResource(
            this,
            R.array.anime_list,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter =adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
                if (position != 0) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    performSearch(selectedItem)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){}
        }
    }
    private fun performSearch(item: String){
        val url = getString(R.string.search_url_template, item)
        val intent = Intent(Intent.ACTION_VIEW,url.toUri())
        startActivity(intent)
    }
}