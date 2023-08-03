package com.example.farmerapp.data.repository

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.domain.repository.dao.CompanyDao
import javax.inject.Inject

class CompanyRepositoryImp
@Inject constructor(
    private val companyDao: CompanyDao
) : CompanyRepository {
    override suspend fun insertCompany(company: Company):Long {
        return companyDao.insertCompany(company)
    }

    override suspend fun updateCompany(company: Company):Long {
        return companyDao.updateCompany(company)
    }

    override suspend fun selectCompanyWithCompanyId(companyId: Int): Company {
        return companyDao.selectCompanyWithCompanyId(companyId)
    }
}