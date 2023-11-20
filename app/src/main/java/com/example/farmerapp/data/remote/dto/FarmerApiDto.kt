package com.example.farmerapp.data.remote.dto

data class FarmerApiDto(
    var id: String,
    var companies: List<CompanyApiDto>?,
    var name: String,
    var surname: String,
    var phoneNumber: String,
    var birthDate: String,
    var savedDate: String,
    var profilePhoto: String,
    var farmerStatus: String,
    var password: String
)

