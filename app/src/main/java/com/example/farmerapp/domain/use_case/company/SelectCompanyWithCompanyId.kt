package com.example.farmerapp.domain.use_case.company

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectCompanyWithCompanyId
@Inject constructor(
    private val companyRepository: CompanyRepository
) {
    fun selectCompanyWithCompanyId(companyId: Int) = flow<Resource<Company>> {
        emit(Resource.Loading())
        val company = companyRepository.selectCompanyWithCompanyId(companyId)
        emit(Resource.Success(company))
    }.catch { emit(Resource.Error(it.message)) }
}