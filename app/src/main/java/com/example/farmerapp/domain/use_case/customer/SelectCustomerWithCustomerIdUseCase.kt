package com.example.farmerapp.domain.use_case.customer

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.CustomerRepository
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectCustomerWithCustomerIdUseCase
@Inject constructor(
    private val customerRepository: CustomerRepository
) {
    fun selectCustomerWithCustomerID(customerId: Int) = flow<Resource<Customer>> {
        emit(Resource.Loading())
        val customer = customerRepository.selectCustomerWithCustomerID(customerId)
        emit(Resource.Success(customer.toCustomer()))
    }.catch { emit(Resource.Error(it.message!!)) }
}