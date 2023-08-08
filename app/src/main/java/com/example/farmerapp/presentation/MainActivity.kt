package com.example.farmerapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmerapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}