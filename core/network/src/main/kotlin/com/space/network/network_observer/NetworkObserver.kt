package com.space.network.network_observer

import kotlinx.coroutines.flow.Flow

interface NetworkObserver {
    fun observe(): Flow<NetworkStatus>
}