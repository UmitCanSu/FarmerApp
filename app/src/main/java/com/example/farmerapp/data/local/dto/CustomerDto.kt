package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity("Customer")
data class CustomerDto
@Inject constructor(
    val name: String,
    val sourName: String,
    val phone: String,
    val address: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}