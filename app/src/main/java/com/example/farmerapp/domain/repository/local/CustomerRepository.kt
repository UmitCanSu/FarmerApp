package com.example.farmerapp.domain.repository.local

import com.example.farmerapp.data.local.dto.CustomerDto

interface CustomerRepository {
    suspend fun insertCustomer(customer: CustomerDto): Long
    suspend fun updateCustomer(customer: CustomerDto): Int
    suspend fun selectCustomerWithCustomerID(customerId: Int): CustomerDto
    suspend fun selectCustomerWithPhoneNumber(phoneNumber: String): CustomerDto
    suspend fun getAllCustomer():List<CustomerDto>


}