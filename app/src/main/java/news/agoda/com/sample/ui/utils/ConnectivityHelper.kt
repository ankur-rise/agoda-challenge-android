package news.agoda.com.sample.ui.utils

import android.content.Context
import android.net.ConnectivityManager

fun Context.isConnectedToNetwork():Boolean {
    val cm: ConnectivityManager? = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm?.activeNetworkInfo

    return netInfo != null && netInfo.isConnected
}