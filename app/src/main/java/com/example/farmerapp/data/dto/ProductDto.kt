package com.example.farmerapp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity("Product")
data class ProductDto
@Inject constructor(
    val name: String,
    val unitType: String,
    val price: Int,
    val companyID: Int
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}