package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.remote.api.FarmerAppApi
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.repository.remote.FarmerApiRepository
import javax.inject.Inject

class FarmerApiRepositoryImp
@Inject constructor(
    private val farmerApi: FarmerAppApi
) : FarmerApiRepository {
    override suspend fun addFarmerApi(farmerJson: String): FarmerApiDto {
        return farmerApi.addFarmerToApi(farmerJson)
    }

    override suspend fun login(farmerLoginJson: String): FarmerApiDto {
        return farmerApi.login(farmerLoginJson)
    }

    override suspend fun getFarmerByNickNameOrPhoneNumber(phoneNumberOrNickName: String): FarmerApiDto {
        return farmerApi.getFarmerByNickNameOrPhoneNumber(phoneNumberOrNickName)
    }


}