package com.example.shell

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import android.os.Bundle
import android.util.Log
import android.content.Context
import android.widget.Toast
import com.example.shell.databinding.ActivityMainBinding
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {
    init {
        lifecycleScope.launchWhenCreated {
            val executeCommand = async { shell("uptime") }
            Toast.makeText(this@MainActivity, executeCommand.await(), Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}