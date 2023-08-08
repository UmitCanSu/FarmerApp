package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.LocalDateTime
import javax.inject.Inject


data class AmountPaid
@Inject constructor(
    val salesProduct: SalesProduct?,
    val customer: Customer,
    val price: Int,
    val date: LocalDateTime,
    val location: Float
) {
    val id: Int = 0
}