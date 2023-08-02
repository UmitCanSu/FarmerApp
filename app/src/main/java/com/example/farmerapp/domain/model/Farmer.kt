package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.farmerapp.until.FarmerStatus
import javax.inject.Inject

@Entity
data class Farmer
@Inject constructor(
    val company: Company,
    val name: String,
    val sourName: String,
    val years: String,
    val farmerStatus: FarmerStatus
){
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
