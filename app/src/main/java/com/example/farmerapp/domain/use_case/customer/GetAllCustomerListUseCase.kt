package com.example.farmerapp.domain.use_case.customer

import com.example.farmerapp.domain.model.Customer
import com.example.farmerapp.domain.repository.room.CustomerRepository
import com.example.farmerapp.until.extetensions.CustomerExtensions.toCustomer
import com.example.farmerapp.until.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllCustomerListUseCase
@Inject constructor(
    private val customerRepository: CustomerRepository
) {
    fun getAllCustomersList() = flow<Resource<List<Customer>>> {
        emit(Resource.Loading())
        val customerList = customerRepository.getAllCustomer()
            .map { it.toCustomer() }
            .ifEmpty { emptyList() }
        emit(Resource.Success(customerList))
    }.catch { emit(Resource.Error(it.message!!)) }.flowOn(Dispatchers.IO)
}