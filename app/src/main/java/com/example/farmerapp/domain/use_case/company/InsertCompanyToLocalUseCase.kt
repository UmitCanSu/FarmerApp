package com.example.farmerapp.domain.use_case.company

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.local.CompanyRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompanyDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertCompanyToLocalUseCase
@Inject constructor(
    private val companyRepository: CompanyRepository,
) {
    fun insertCompany(company: Company) = flow<Resource<Company>> {
        emit(Resource.Loading())
        val insertSize = companyRepository.insertCompany(company.toCompanyDto())
        company.id = insertSize.toInt()
        if (insertSize > 0)
            emit(Resource.Success(company))
        else {
            emit(Resource.Error("Can not save company"))
        }
    }.catch { emit(Resource.Error(it.message!!)) }.flowOn(Dispatchers.IO)
}

