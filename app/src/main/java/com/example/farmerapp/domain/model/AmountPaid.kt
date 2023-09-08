package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import java.sql.Date
import java.time.LocalDateTime
import javax.inject.Inject


data class AmountPaid
@Inject constructor(
    val salesProduct: SalesProduct?,
    val customer: Customer,
    val price: Float,
    val date: LocalDateTime,
    val location: LatLng
) {
    val id: Int = 0
}