package com.example.farmerapp.data.remote.dto

data class CustomerListApiDto(
    val companyId: String,
    val customers: List<FarmerApiDto>,

)
