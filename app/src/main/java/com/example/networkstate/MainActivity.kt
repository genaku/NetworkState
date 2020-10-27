package com.example.networkstate

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.genaku.networkstate.AbstractNetworkStateFlow
import com.genaku.networkstate.getNetworkStateFlow
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var onlineState: AbstractNetworkStateFlow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvOnline = findViewById<TextView>(R.id.tvOnline)
        onlineState = getNetworkStateFlow(this)
        lifecycleScope.launchWhenResumed {
            onlineState.collect {
                tvOnline.text = if (it) "ONLINE" else "offline"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        onlineState.start()
    }

    override fun onPause() {
        onlineState.stop()
        super.onPause()
    }
}