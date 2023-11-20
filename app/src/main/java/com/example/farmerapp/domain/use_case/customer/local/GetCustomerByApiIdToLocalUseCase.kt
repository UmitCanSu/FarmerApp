package com.example.farmerapp.domain.use_case.customer.local

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.local.CustomerRepository
import com.example.farmerapp.until.Resource
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomerDto
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCustomerByApiIdToLocalUseCase
@Inject constructor(
    private val customerRepository: CustomerRepository
) {
    fun getCustomerByApiId(apiId: String) = flow<Resource<Customer>> {
        emit(Resource.Loading())
        val customerDto = customerRepository.getCustomerByApiId(apiId)
        val customer = customerDto.toCustomer()
        emit(Resource.Success(customer))
    }.catch { emit(Resource.Error(it.message!!)) }
}