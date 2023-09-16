package com.example.farmerapp.domain.use_case.company

import com.example.farmerapp.data.remote.dto.CompanyApiDto
import com.example.farmerapp.data.remote.api.CompanyAppApi
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCompanyToApi
@Inject constructor(
    private val farmerAppApi: CompanyAppApi
) {
    fun getCompanyToApi(mongoCompanyId:String) = flow<Resource<CompanyApiDto>> {
        emit(Resource.Loading())
        val company = farmerAppApi.getCompany(mongoCompanyId)
        emit(Resource.Success(company!!))
    }.catch { emit(Resource.Error(it.message!!)) }
}