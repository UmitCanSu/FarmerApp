package com.example.farmerapp.data.repository

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.domain.repository.dao.CompanyDao
import javax.inject.Inject

class CompanyRepositoryImp
@Inject constructor(
    private val companyDao: CompanyDao
) : CompanyRepository {
    override suspend fun insertCompany(company: CompanyDto):Long {
        return companyDao.insertCompany(company)
    }

    override suspend fun updateCompany(company: CompanyDto):Int {
        return companyDao.updateCompany(company)
    }

    override suspend fun selectCompanyWithCompanyId(companyId: Int): CompanyDto {
        return companyDao.selectCompanyWithCompanyId(companyId)
    }
}