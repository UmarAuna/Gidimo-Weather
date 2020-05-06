package com.poem.gidimoweather.utils

import android.content.Context
import android.net.ConnectivityManager

class Is_Network {
    fun isOnline(contextValue: Context): Boolean {
        val cm = contextValue.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}