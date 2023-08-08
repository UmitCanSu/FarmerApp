package com.example.farmerapp.domain.use_case.company

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.until.Extetensions.CompanyExtensions.toCompanyDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertCompanyUseCase
@Inject constructor(
    private val companyRepository: CompanyRepository,
) {
    fun insertCompany(company: Company) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val insertSize = companyRepository.insertCompany(company.toCompanyDto())

        emit(Resource.Success(insertSize > 0))
    }.catch { emit(Resource.Error(it.message!!)) }.flowOn(Dispatchers.IO)
}

