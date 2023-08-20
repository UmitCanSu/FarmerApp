package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import javax.inject.Inject

@Entity(tableName = "SalesProduct")
data class SalesProductDto
@Inject constructor(
    val companyId: Int,
    val productId: Int,
    val customerId: Int,
    val farmerId: Int,
    val price: Int,
    val isDept: Boolean,
    val productNumber: Int,
    val location: Float,
    val locationDescription: String,
    val salesDate: LocalDateTime,
    val isPaid: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}