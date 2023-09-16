package com.example.farmerapp.domain.use_case.company

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.local.CompanyRepository
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompanyDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateCompanyUseCase
@Inject constructor(
    private val companyRepository: CompanyRepository
) {
    fun updateCompany(company: Company) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val updateSize = companyRepository.updateCompany(company.toCompanyDto())
        emit(Resource.Success(updateSize > 0))
    }.catch { emit(Resource.Error(it.message!!)) }
}