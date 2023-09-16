package com.example.farmerapp.data.remote.api

import com.example.farmerapp.data.remote.dto.CompanyApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface CompanyAppApi {
    @POST("Company/AddCompany")
    suspend fun addCompany(
        @Query("companyJson") companyJson: String,
    ): CompanyApiDto

    @POST("Company/GetCompanyById")
    suspend fun getCompany(
        @Query("companyId") companyId: String
    ): CompanyApiDto
}