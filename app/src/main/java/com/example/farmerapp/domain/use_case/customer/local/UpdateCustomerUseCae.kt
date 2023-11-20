package com.example.farmerapp.domain.use_case.customer.local

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.local.CustomerRepository
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomerDto
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateCustomerUseCae
@Inject constructor(
    private val customerRepository: CustomerRepository
) {
    fun updateCustomer(customer: Customer) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val updatedSize = customerRepository.updateCustomer(customer.toCustomerDto())
        emit(Resource.Success(updatedSize > 0))
    }.catch { emit(Resource.Error(it.message!!)) }
}