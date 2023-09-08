package com.example.farmerapp.domain.repository.api

import com.example.farmerapp.data.remote.dto.FarmerApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface FarmerAppApi {
    @POST("Farmer/AddFarmer2")
    suspend fun addFarmerToApi(
        @Query("farmerJson") farmerJson: String
    ): FarmerApiDto
    @POST("Login/loginJson")
    suspend fun login(
        @Query("farmerLoginJson") loginJson: String,
    ): FarmerApiDto
}