package com.example.farmerapp.domain.use_case.customer.remote

import com.example.farmerapp.data.remote.api.CustomerApi
import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.FarmerExtensions.toCustomer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCustomerByCompanyIdToApiUseCase
@Inject constructor(
    private val customerApi: CustomerApi
) {
    fun getCustomerByCompanyIdToApi(companyId: String) = flow<Resource<List<Customer>>> {
        emit(Resource.Loading())
        val customerApiDto = customerApi.getCustomerList(companyId)
        val customers = customerApiDto.customers.map { it.toCustomer() }
        emit(Resource.Success(customers))
    }.catch { emit(Resource.Error(it.message!!)) }
}