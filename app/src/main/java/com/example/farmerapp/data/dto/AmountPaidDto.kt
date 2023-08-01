package com.example.farmerapp.data.dto

import androidx.room.PrimaryKey
import java.sql.Date
import javax.inject.Inject

data class AmountPaidDto
@Inject constructor(
    val salesProductId: Int,
    val price: Int,
    val date: Date,
) {
    @PrimaryKey(autoGenerate = true)
    val id = 0
}