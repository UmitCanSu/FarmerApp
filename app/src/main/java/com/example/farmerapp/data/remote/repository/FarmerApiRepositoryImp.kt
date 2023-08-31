package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.repository.api.repository.FarmerApiRepository
import com.example.farmerapp.domain.repository.api.FarmerAppApi
import javax.inject.Inject

class FarmerApiRepositoryImp
    @Inject constructor(
    private val farmerApi:FarmerAppApi
): FarmerApiRepository {
    override suspend fun addFarmerApi(farmerJson: String): FarmerApiDto {
        return farmerApi.addFarmerToApi(farmerJson)
    }

    override suspend fun login(farmerLoginJson: String): FarmerApiDto {
        return farmerApi.login(farmerLoginJson)
    }

}