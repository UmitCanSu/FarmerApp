package com.example.farmerapp.domain.repository.api

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import retrofit2.http.POST
import retrofit2.http.Query

interface CompanyAppApi {
    @POST("Company/AddCompany2")
    suspend fun addCompany(
        @Query("address") address: String,
        @Query("phoneNumber") phoneNumber: String,
        @Query("name") name: String
    ): CompanyDto

    @POST("Company/GetCompanyById")
    suspend fun getCompany(
        @Query("companyId") companyId:String
    ): CompanyApiDto
}