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
    override suspend fun insertFarmer(farmer: Farmer):Long {
        return farmerDao.insertFarmer(farmer)
    }

    override suspend fun updateFarmer(farmer: Farmer):Int {
        return farmerDao.updateFarmer(farmer)
    }

    override suspend fun deleteFarmer(farmer: Farmer):Int {
        return farmerDao.deleteFarmer(farmer)
    }

    override suspend fun selectFarmerWithId(farmerId: Int): FarmerRelations {
        return farmerDao.selectFarmerWithId(farmerId)
    }

    override suspend fun selectFarmersWithCompanyId(companyId: Int): List<FarmerRelations> {
        return farmerDao.selectFarmersWithCompanyId(companyId)
    }

}