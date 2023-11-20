package com.example.farmerapp.domain.repository.local

import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations

interface FarmerRepository {
    suspend fun insertFarmer(farmerDto: FarmerDto): Long
    suspend fun updateFarmer(farmerDto: FarmerDto): Int
    suspend fun deleteFarmer(farmerDto: FarmerDto): Int
    suspend fun selectFarmerById(farmerId: Int): FarmerRelations
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<FarmerRelations>
    suspend fun selectFarmerByApiId(farmerApiId:String):FarmerRelations

}