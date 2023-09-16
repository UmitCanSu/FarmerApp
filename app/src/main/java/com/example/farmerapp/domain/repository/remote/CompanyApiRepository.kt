package com.example.farmerapp.domain.repository.remote

import com.example.farmerapp.data.remote.dto.CompanyApiDto

interface CompanyApiRepository {
    suspend fun addCompany(companyJson:String):CompanyApiDto
    suspend fun getCompany(companyId: String): CompanyApiDto
}