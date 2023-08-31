package com.example.farmerapp.domain.use_case.customer

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.api.CustomerApi
import com.example.farmerapp.domain.repository.api.repository.CustomerApiRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomerApiDto
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddCustomerToApiUseCase
@Inject constructor(
    private val customerApiRepository: CustomerApiRepository
) {
    fun addCustomer(customer: Customer) = flow<Resource<Customer>> {
        emit(Resource.Loading())
        val customerToCustomerApiDto = customer.toCustomerApiDto()
        val customerApiDtoToJson = Gson().toJson(customerToCustomerApiDto)
        val customer = customerApiRepository.addCustomer(customerApiDtoToJson).toCustomer()
        emit(Resource.Success(customer))
    }.catch { emit(Resource.Error(it.message!!)) }
}