package com.example.farmerapp.domain.repository.api.repository

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.domain.model.Company

interface CompanyApiRepository {
    suspend fun addCompany(company: Company):CompanyDto
    suspend fun getCompany(companyId: String): CompanyApiDto
}