package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class Customer
@Inject constructor(
    val name: String,
    val sourName: String,
    val phone: String,
    val address: String,
    @PrimaryKey(autoGenerate = false)
    val phoneNumber: String
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
