package com.genaku.networkstate

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class AbstractNetworkStateFlow(context: Context) : StateFlow<Boolean> {

    abstract fun stop()

    private val innerState = MutableStateFlow(false)

    protected var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val value: Boolean
        get() = innerState.value

    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<Boolean>) {
        innerState.collect(collector)
    }

    override val replayCache: List<Boolean>
        get() = innerState.replayCache

    open fun start() {
        postValue(isOnline())
    }

    protected fun postValue(value: Boolean) {
        innerState.value = value
    }

    @Suppress("DEPRECATION")
    protected fun isOnline(): Boolean {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}
