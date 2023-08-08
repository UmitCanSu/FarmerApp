package com.example.farmerapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.farmerapp.until.FarmerStatus
import javax.inject.Inject

data class Farmer
@Inject constructor(
    var company: Company?,
    var name: String,
    var sourName: String,
    var years: Int,
    var farmerStatus: FarmerStatus
){
    var id: Int = 0
}