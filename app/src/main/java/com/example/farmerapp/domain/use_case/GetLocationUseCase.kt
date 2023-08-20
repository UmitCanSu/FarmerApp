package com.example.farmerapp.domain.use_case

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.farmerapp.R
import com.example.farmerapp.until.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationUseCase
@Inject constructor(
    @ApplicationContext private val context: Context,
    private val application: Application
) {
    fun getLocation() = flow<Resource<LatLng>> {
        emit(Resource.Loading())
        val locationManager: LocationManager =
            application.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val laL = MutableLiveData<LatLng>()
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f) {
                val latLng = LatLng(it.latitude, it.longitude)
                laL.value = latLng
            }
       //     emit(Resource.Success(laL.value!!))

        } else {
            emit(Resource.Error(context.getString(R.string.permission_denied)))
        }
    }.catch { emit(Resource.Error(it.message!!)) }
}
