package com.example.farmerapp.domain.use_case.customer

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.CompanyRepository
import com.example.farmerapp.domain.repository.CustomerRepository
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateCustomer
@Inject constructor(
    private val customerRepository: CustomerRepository
) {
    fun updateCustomer(customer: Customer) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        val updatedSize = customerRepository.updateCustomer(customer)
        emit(Resource.Success(updatedSize > 0))
    }.catch { emit(Resource.Error(it.message)) }
}