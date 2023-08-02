package com.example.farmerapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
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
    val date: Date,
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
