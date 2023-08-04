package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.domain.model.Farmer

interface FarmerRepository {
    suspend fun insertFarmer(farmerDto: FarmerDto): Long
    suspend fun updateFarmer(farmerDto: FarmerDto): Int
    suspend fun deleteFarmer(farmerDto: FarmerDto): Int
    suspend fun selectFarmerWithId(farmerId: Int): FarmerRelations
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<FarmerRelations>

}