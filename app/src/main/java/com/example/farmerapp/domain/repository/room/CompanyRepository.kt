package com.example.farmerapp.domain.repository.room

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.domain.model.Company

interface CompanyRepository {
    suspend fun insertCompany(company: CompanyDto):Long
    suspend fun updateCompany(company: CompanyDto):Int
    suspend fun selectCompanyWithCompanyId(companyId: Int): CompanyDto
}