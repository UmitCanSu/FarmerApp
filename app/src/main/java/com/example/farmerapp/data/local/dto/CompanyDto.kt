package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity("Company")
data class CompanyDto
@Inject constructor(
    val name: String,
    val address: String,
    val phone: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
