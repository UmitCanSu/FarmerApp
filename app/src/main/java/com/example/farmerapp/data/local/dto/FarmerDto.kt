package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject
@Entity(tableName = "Farmer")
data class FarmerDto
@Inject constructor(
    val companyId: Int,
    val name: String,
    val sourName: String,
    val years: Int,
    val farmerStatus: Int,
    val farmerApiId:String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

