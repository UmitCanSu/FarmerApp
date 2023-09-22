package com.example.farmerapp.data.local.repository

import com.example.farmerapp.data.local.dto.CompanyDto
import com.example.farmerapp.domain.repository.dao.CompanyDao
import com.example.farmerapp.domain.repository.local.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImp
@Inject constructor(
    private val companyDao: CompanyDao
) : CompanyRepository {
    override suspend fun insertCompany(company: CompanyDto): Long {
        return companyDao.insertCompany(company)
    }

    override suspend fun updateCompany(company: CompanyDto): Int {
        return companyDao.updateCompany(company)
    }

    override suspend fun selectCompanyByCompanyId(companyId: Int): CompanyDto? {
        return companyDao.selectCompanyByCompanyId(companyId)
    }

    override fun selectCompanyByPhoneNumberAndName(phoneNumber: String, name: String): CompanyDto? {
        return companyDao.selectCompanyByPhoneNumberAndName(phoneNumber, name)
    }
}