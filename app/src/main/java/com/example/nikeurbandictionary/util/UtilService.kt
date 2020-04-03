package com.example.nikeurbandictionary.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class UtilService {
    companion object {
        fun hasNetworkConnection(context: Context): Boolean? {
            var hasConnection: Boolean? = false
            val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null) {
                if (activeNetwork.isConnected) hasConnection = true
            }
            return hasConnection
        }
    }
}