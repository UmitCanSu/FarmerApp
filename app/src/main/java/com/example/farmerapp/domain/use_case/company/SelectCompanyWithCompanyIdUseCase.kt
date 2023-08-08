package com.example.farmerapp.domain.use_case.company

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.until.Extetensions.CompanyExtensions.toCompany
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SelectCompanyWithCompanyIdUseCase
@Inject constructor(
    private val companyRepository: CompanyRepository
) {
    fun selectCompanyWithCompanyId(companyId: Int) = flow<Resource<Company?>> {
        emit(Resource.Loading())
        val company = companyRepository.selectCompanyWithCompanyId(companyId)
        if (company == null){
            emit(Resource.Success(null))
        }else{
            emit(Resource.Success(company.toCompany()))
        }
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)

}