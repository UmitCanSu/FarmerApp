package com.example.farmerapp.domain.use_case.customer.remote

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.remote.CustomerApiRepository
import com.example.farmerapp.until.Resource

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddCuromerToApiUseCase
@Inject constructor(
    private val customerApiRepository: CustomerApiRepository
) {
    fun addCustomer(customer: Customer) = flow<Resource<Customer>> {/*
        emit(Resource.Loading())
        val customerToCustomerApiDto = customer.toCustomerApiDto()
        val customerApiDtoToJson = Gson().toJson(customerToCustomerApiDto)
        val customer = customerApiRepository.addCustomer(customerApiDtoToJson).toCustomer()
        */
        emit(Resource.Success(customer))
    }.catch { emit(Resource.Error(it.message!!)) }
}