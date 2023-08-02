package com.example.farmerapp.data.repository

import com.example.farmerapp.data.dto.FarmerDto
import com.example.farmerapp.domain.model.Farmer
import com.example.farmerapp.domain.repository.FarmerRepository
import com.example.farmerapp.domain.repository.dao.FarmerDao
import javax.inject.Inject

class FarmerRepositoryImp
@Inject constructor(
    private val farmerDao: FarmerDao
) : FarmerRepository {
    override suspend fun insertFarmer(farmer: Farmer) {
        return farmerDao.insertFarmer(farmer)
    }

    override suspend fun updateFarmer(farmer: Farmer) {
        return farmerDao.updateFarmer(farmer)
    }

    override suspend fun deleteFarmer(farmer: Farmer) {
        return farmerDao.deleteFarmer(farmer)
    }

    override suspend fun selectFarmerWithId(farmerId: Int): FarmerDto {
        return farmerDao.selectFarmerWithId(farmerId)
    }

    override suspend fun selectFarmersWithCompanyId(companyId: Int): List<FarmerDto> {
        return farmerDao.selectFarmersWithCompanyId(companyId)
    }

}