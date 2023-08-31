package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.LocalDateTime
import javax.inject.Inject

@Entity(tableName = "AmountPaid")
data class AmountPaidDto
@Inject constructor(
    val salesProductId: Int,
    val customerId: Int,
    val price: Float,
    val date: LocalDateTime,
    val location: Float,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}