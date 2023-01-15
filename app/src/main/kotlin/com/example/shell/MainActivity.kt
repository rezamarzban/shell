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
            val context = this@MainActivity
            arrayOf(
            "getprop ro.vendor.product.model",
            "getprop ro.build.version.release",
            "date",
            "ls -R -1 -h -g ${context.filesDir.absolutePath}",
            "cat /proc/cpuinfo", "cat /proc/meminfo",
            "free", "cat /proc/version",
            "echo ${context.filesDir.absolutePath}"
            ).forEach { command ->
                Toast.makeText(context, async { shell(command) }.await(), Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
