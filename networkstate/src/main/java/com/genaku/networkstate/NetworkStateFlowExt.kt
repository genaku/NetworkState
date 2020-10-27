package com.genaku.networkstate

import android.content.Context
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
val defaultNetworkRequest: NetworkRequest = NetworkRequest.Builder()
    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .addTransportType(NetworkCapabilities.TRANSPORT_BLUETOOTH)
    .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
    .build()

/**
 * Get network online state coroutine state flow
 *
 * @return network online state coroutine state flow
 */
fun getNetworkStateFlow(context: Context): INetworkStateFlow =
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> NougatNetworkStateFlow(context)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> MarshmallowNetworkStateFlow(context)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> LollipopNetworkStateFlow(context)
        else -> KitkatNetworkStateFlow(context)
    }