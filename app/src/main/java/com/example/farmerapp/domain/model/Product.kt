package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject


data class Product
@Inject constructor(
    val name:String,
    val unitType:String,
    val price:Int,
    val company: Company
){
    var id: Int = 0
}
