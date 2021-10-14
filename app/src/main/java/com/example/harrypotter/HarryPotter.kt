package com.example.harrypotter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.harrypotter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HarryPotter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}