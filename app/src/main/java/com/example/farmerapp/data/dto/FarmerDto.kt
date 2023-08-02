package com.example.farmerapp.data.dto

import androidx.room.Entity
import javax.inject.Inject
@Entity(tableName = "Farmer")
data class FarmerDto
@Inject constructor(
    val companyId: Int,
    val name: String,
    val sourName: String,
    val years: String,
    val farmerStatus: Int
)

