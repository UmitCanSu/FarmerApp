package com.example.farmerapp.domain.repository

import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.domain.model.Customer

interface CustomerRepository {
    suspend fun insertCustomer(customer: CustomerDto):Long
    suspend fun updateCustomer(customer: CustomerDto):Int
    suspend fun selectCustomerWithCustomerID(customerId: Int): CustomerDto


}