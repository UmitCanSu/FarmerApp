package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import javax.inject.Inject

@Entity(tableName = "AmountPaid")
data class AmountPaidDto
@Inject constructor(
    val salesProductId: Int,
    val customerId: Int,
    val price: Int,
    val date: Date,
    val location: Float,
) {
    @PrimaryKey(autoGenerate = true)
    val id = 0
}