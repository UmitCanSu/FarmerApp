package com.example.farmerapp.domain.use_case

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.farmerapp.until.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class IsInternetUseCase(
    @ApplicationContext private val context: Context
) {
    fun isInternet() = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    )
                ) {
                    emit(Resource.Success(true))
                } else {
                    emit(Resource.Success(false))
                }
            } else {
                emit(Resource.Success(false))
            }
        } else {
            emit(Resource.Success(false))
        }
    }.catch { emit(Resource.Error(it.message!!)) }
}