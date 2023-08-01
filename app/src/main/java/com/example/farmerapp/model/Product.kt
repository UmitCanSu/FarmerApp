package com.example.farmerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class Product
@Inject constructor(
    val name:String,
    val unitType:String
){
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
