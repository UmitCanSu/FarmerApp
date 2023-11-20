package com.example.farmerapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

@Entity("Company")
data class CompanyDto
@Inject constructor(
    val name: String,
    val address: String,
    @SerializedName("phoneNumber")
    val phone: String,
    @SerializedName("id")
    val apiId:String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    constructor(
        name: String,
        address: String,
        phone: String,
    ):this(name, address, phone, "")
}
