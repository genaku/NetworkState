package com.genaku.networkstate

import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for network online state observer
 *
 * @author Gena Kuchergin
 */
interface INetworkStateFlow: StateFlow<Boolean> {

    /**
     * Start observing
     */
    fun start()

    /**
     * Stop observing
     */
    fun stop()
}