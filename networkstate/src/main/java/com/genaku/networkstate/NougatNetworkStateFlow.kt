package com.genaku.networkstate

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class NougatNetworkStateFlow(context: Context) : AbstractNetworkStateFlow(context) {

    private val connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            networkCapabilities.let { capabilities ->
                if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                ) {
                    postValue(true)
                }
            }
        }

        override fun onLost(network: Network) {
            postValue(false)
        }
    }

    override fun start() {
        super.start()
        connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback)
    }

    override fun stop() {
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }
}