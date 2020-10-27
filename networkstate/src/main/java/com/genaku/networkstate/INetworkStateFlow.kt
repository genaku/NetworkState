package com.genaku.networkstate

import kotlinx.coroutines.flow.StateFlow

interface INetworkStateFlow: StateFlow<Boolean> {
    fun start()
    fun stop()
}