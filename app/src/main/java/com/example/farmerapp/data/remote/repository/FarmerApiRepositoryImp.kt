package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.Login
import com.example.farmerapp.data.remote.dto.FarmerApiDto
import com.example.farmerapp.domain.repository.api.FarmerApiRepository
import com.example.farmerapp.domain.repository.api.FarmerAppApi
import com.google.gson.Gson
import javax.inject.Inject
import kotlin.math.log

class FarmerApiRepositoryImp
    @Inject constructor(
    private val farmerApi:FarmerAppApi
):FarmerApiRepository {
    override suspend fun addFarmerApi(farmerJson: String): FarmerApiDto {
        return farmerApi.addFarmerToApi(farmerJson)
    }

    override suspend fun login(farmerLoginJson: String): FarmerApiDto {
        return farmerApi.login(farmerLoginJson)
    }

}