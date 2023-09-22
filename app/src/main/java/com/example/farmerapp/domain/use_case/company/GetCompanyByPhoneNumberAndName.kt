package com.example.farmerapp.domain.use_case.company

import com.example.farmerapp.domain.model.Company
import com.example.farmerapp.domain.repository.local.CompanyRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.CompanyExtensions.toCompany
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCompanyByPhoneNumberAndName
@Inject constructor(
    private val companyRepository: CompanyRepository
) {
    fun getCompanyByPhoneNumberAndName(phoneNumber: String, name: String) =
        flow<Resource<Company?>> {
            emit(Resource.Loading())
            val getCompany =
                companyRepository.selectCompanyByPhoneNumberAndName(phoneNumber, name)?.toCompany()
            emit(Resource.Success(getCompany))
        }.catch { emit(Resource.Error(it.message!!)) }
}