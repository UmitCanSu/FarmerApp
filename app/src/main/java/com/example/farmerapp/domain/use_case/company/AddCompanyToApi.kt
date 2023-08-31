package com.example.farmerapp.domain.use_case.company

import android.util.Log
import com.example.farmerapp.domain.repository.api.CompanyApiRepository
import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.until.Resource
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
    fun addCompanyToApi(company: Company) = flow{
        emit(Resource.Loading())
        val companyDto = companyApiRepository.addCompany(company)
        Log.e("S->",Gson().toJson(companyDto))
        emit(Resource.Success(true))
    }.catch { emit(Resource.Error(it.message!!)) }.flowOn(Dispatchers.IO)
}