package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.domain.repository.api.CompanyAppApi
import com.example.farmerapp.domain.repository.api.repository.CompanyApiRepository
import com.example.farmerapp.domain.model.Company
import javax.inject.Inject

class CompanyApiRepositoryImp
@Inject constructor(
    private val companyApi: CompanyAppApi
): CompanyApiRepository {
    override suspend fun addCompany(companyJson: String): CompanyApiDto {
        return companyApi.addCompany(companyJson)
    }

    override suspend fun getCompany(companyId: String): CompanyApiDto {
        return companyApi.getCompany(companyId)
    }
}