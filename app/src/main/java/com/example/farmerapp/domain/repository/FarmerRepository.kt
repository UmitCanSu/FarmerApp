package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.dto.FarmerDto
import com.example.farmerapp.domain.model.Farmer

interface FarmerRepository {
    suspend fun insertFarmer(farmer: Farmer)
    suspend fun updateFarmer(farmer: Farmer)
    suspend fun deleteFarmer(farmer: Farmer)
    suspend fun selectFarmerWithId(farmerId:Int): FarmerDto
    suspend fun selectFarmersWithCompanyId(companyId:Int): List<FarmerDto>

}