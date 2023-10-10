package com.example.farmerapp.domain.repository.remote

import com.example.farmerapp.data.remote.dto.FarmerApiDto

interface FarmerApiRepository {
    suspend fun addFarmerApi(farmerJson: String): FarmerApiDto
    suspend fun login(farmerLoginJson:String): FarmerApiDto
    suspend fun  getFarmerByPhoneNumber(phoneNumber:String):FarmerApiDto
}