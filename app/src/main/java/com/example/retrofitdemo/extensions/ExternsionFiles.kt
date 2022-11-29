package com.example.retrofitdemo.extensions

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.Toast

fun View.beVisible(){
    this.visibility = View.VISIBLE
}

fun View.beGone(){
    this.visibility = View.GONE
}

fun Context.showMessage(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun hasInternetConnection(application: Application): Boolean {
    val connectivityManager = application.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.activeNetworkInfo?.run {
            return when(type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_ETHERNET -> true
                else -> false
            }
        }
    }
    return false
}