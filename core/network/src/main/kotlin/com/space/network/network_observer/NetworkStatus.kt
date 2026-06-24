package com.space.network.network_observer

sealed class NetworkStatus {
    object Available : NetworkStatus()

    object Unavailable : NetworkStatus()
}