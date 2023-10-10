package com.example.farmerapp.data.remote.dto

import com.google.android.gms.maps.model.LatLng

data class SavedApiDto(
    val farmer: FarmerApiDto,
    val date: String,
    val location: LatLng
)