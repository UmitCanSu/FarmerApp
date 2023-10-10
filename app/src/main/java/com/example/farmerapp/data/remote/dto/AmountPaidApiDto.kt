package com.example.farmerapp.data.remote.dto

import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime

data class AmountPaidApiDto(
    val id: String,
    val customer: FarmerApiDto,
    val price: Float,
    val paidDate: String,
    val location: LatLng,
)
