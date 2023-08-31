package com.example.farmerapp.data.remote

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
    override suspend fun addCompany(company: Company): CompanyDto {
        return companyApi.addCompany(company.address,company.phone,company.name)
        //return companyApi.addCompany(company)
    }

    override suspend fun getCompany(companyId: String): CompanyApiDto {
        return companyApi.getCompany(companyId)
    }
}