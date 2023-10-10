package com.example.farmerapp.domain.use_case.customer

import com.example.farmerapp.domain.repository.local.CustomerRepository
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SelectCustomerWithPhoneNumber
@Inject constructor(
    private val customerRepository: CustomerRepository
) {
    fun selectCustomerWithPhoneNumber(phoneNumber: String) = flow<Resource<Customer?>> {
        emit(Resource.Loading())
        val customerDto = customerRepository.selectCustomerWithPhoneNumber(phoneNumber)

        if (customerDto != null) {
            emit(Resource.Success(customerDto.toCustomer()))
        } else {
            emit(Resource.Success(null))
        }
    }
        .catch { emit(Resource.Error(it.message!!)) }
        .flowOn(Dispatchers.IO)
}