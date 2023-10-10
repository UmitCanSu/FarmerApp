package com.example.farmerapp.data.remote.dto

data class ProductApiDto(
    var id: String?,
    var name: String,
    var description: String,
    var price: Float,
    var unitType: String,
    var savedDate: String,
    var companyId: String,
    val farmerId: String
)