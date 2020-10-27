package com.genaku.networkstate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

class KitkatNetworkStateFlow(private val context: Context) : AbstractNetworkStateFlow(context) {

    private val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            postValue(isOnline())
        }
    }

    override fun start() {
        super.start()
        context.registerReceiver(networkReceiver, filter)
    }

    override fun stop() {
        context.unregisterReceiver(networkReceiver)
    }
}
