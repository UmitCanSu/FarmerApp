package com.example.farmerapp.data.repository

import com.example.farmerapp.data.local.dto.FarmerDto
import com.example.farmerapp.data.local.relations.FarmerRelations
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.FarmerRepository
import com.example.farmerapp.domain.repository.dao.FarmerDao
import javax.inject.Inject

class FarmerRepositoryImp
@Inject constructor(
    private val farmerDao: FarmerDao
) : FarmerRepository {
    override suspend fun insertFarmer(farmerDto: FarmerDto):Long {
        return farmerDao.insertFarmer(farmerDto)
    }

    override suspend fun updateFarmer(farmerDto: FarmerDto):Int {
        return farmerDao.updateFarmer(farmerDto)
    }

    override suspend fun deleteFarmer(farmerDto: FarmerDto):Int {
        return farmerDao.deleteFarmer(farmerDto)
    }

    override suspend fun selectFarmerWithId(farmerId: Int): FarmerRelations {
        return farmerDao.selectFarmerWithId(farmerId)
    }

    override suspend fun selectFarmersWithCompanyId(companyId: Int): List<FarmerRelations> {
        return farmerDao.selectFarmersWithCompanyId(companyId)
    }

}