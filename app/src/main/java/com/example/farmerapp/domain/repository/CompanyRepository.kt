package com.example.farmerapp.domain.repository

import com.example.farmerapp.domain.model.Company

interface CompanyRepository {
    suspend fun insertCompany(company: Company)
    suspend fun updateCompany(company: Company)
    suspend fun selectCompanyWithCompanyId(companyId: Int): Company
}