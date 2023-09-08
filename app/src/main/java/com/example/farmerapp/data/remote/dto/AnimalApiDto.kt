package com.example.farmerapp.data.remote.dto

data class AnimalApiDto(
    var id: String,
    var companyId: String,
    var addedFarmerId: String,
    var name: String,
    var birthDate: String,
    var motherId: String?,
    var fatherId: String?,
    var genderIndex: Int,
)