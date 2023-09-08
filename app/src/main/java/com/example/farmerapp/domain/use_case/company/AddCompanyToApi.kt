package com.example.farmerapp.domain.use_case.company

import android.util.Log
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.api.repository.CompanyApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompanyApiDto
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddCompanyToApi
@Inject constructor(
    private val companyApiRepository: CompanyApiRepository
) {
    fun addCompanyToApi(company: Company) = flow {
        emit(Resource.Loading())
        val companyApiDto = company.toCompanyApiDto()
        val companyApiDtoToJson = Gson().toJson(companyApiDto)
        val companyDto = companyApiRepository.addCompany(companyApiDtoToJson)
        emit(Resource.Success(companyDto!=null))
    }.catch { emit(Resource.Error(it.message!!)) }.flowOn(Dispatchers.IO)
}