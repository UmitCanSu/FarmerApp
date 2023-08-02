package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class Company
@Inject constructor(
    val name: String,
    val address: String,
    val phone: String
){
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}