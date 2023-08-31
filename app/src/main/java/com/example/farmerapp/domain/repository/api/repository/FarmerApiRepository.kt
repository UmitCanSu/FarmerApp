package com.example.farmerapp.domain.repository.api.repository

import com.example.farmerapp.data.Login
import com.example.farmerapp.data.remote.dto.FarmerApiDto

interface FarmerApiRepository {
    suspend fun addFarmerApi(farmerJson: String): FarmerApiDto
    suspend fun login(farmerLoginJson:String): FarmerApiDto
}