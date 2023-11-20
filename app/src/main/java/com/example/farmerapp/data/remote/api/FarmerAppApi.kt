package com.example.farmerapp.data.remote.api

import com.example.farmerapp.data.remote.dto.FarmerApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface FarmerAppApi {
    @POST("Farmer/AddFarmer2")
    suspend fun addFarmerToApi(
        @Query("farmerJson") farmerJson: String
    ): FarmerApiDto

    @POST("Login/FarmerLogin")
    suspend fun login(
        @Query("loginJson") loginJson: String,
    ): FarmerApiDto

    @POST("Farmer/GetFarmerByNickNameOrPhoneNumber")
    suspend fun getFarmerByNickNameOrPhoneNumber(
        @Query("nickNameOrPhoneNumber") nickNameOrPhoneNumber: String,
    ): FarmerApiDto
}