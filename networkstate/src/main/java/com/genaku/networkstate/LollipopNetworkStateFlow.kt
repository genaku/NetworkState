package com.genaku.networkstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class LollipopNetworkStateFlow(context: Context) : AbstractNetworkStateFlow(context) {

    private val connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            postValue(true)
        }

        override fun onLost(network: Network) {
            postValue(false)
        }
    }

    override fun start() {
        super.start()
        connectivityManager.registerNetworkCallback(
            defaultNetworkRequest,
            connectivityManagerCallback
        )
    }

    override fun stop() {
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }
}
