package com.example.farmerapp.domain.repository.local

import com.example.farmerapp.data.local.dto.CompanyDto

interface CompanyRepository {
    suspend fun insertCompany(company: CompanyDto):Long
    suspend fun updateCompany(company: CompanyDto):Int
    suspend fun selectCompanyByCompanyId(companyId: Int): CompanyDto?
    fun selectCompanyByPhoneNumberAndName(phoneNumber: String, name: String): CompanyDto?
}