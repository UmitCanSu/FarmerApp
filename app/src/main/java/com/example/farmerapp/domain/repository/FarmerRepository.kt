package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.domain.model.Farmer

interface FarmerRepository {
    suspend fun insertFarmer(farmer: Farmer): Long
    suspend fun updateFarmer(farmer: Farmer): Int
    suspend fun deleteFarmer(farmer: Farmer): Int
    suspend fun selectFarmerWithId(farmerId: Int): FarmerRelations
    suspend fun selectFarmersWithCompanyId(companyId: Int): List<FarmerRelations>

}