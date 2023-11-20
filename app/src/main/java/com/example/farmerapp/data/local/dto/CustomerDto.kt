package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.farmerapp.until.FarmerStatus
import javax.inject.Inject

@Entity("Customer")
data class CustomerDto
@Inject constructor(
    var name: String,
    var sourName: String,
    var years: Int,
    var farmerStatus: FarmerStatus,
    var customerApiId: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}