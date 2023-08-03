package com.example.farmerapp.domain.repository

import com.example.farmerapp.domain.model.Customer

interface CustomerRepository {
    suspend fun insertCustomer(customer: Customer):Long
    suspend fun updateCustomer(customer: Customer):Int
    suspend fun selectCustomerWithCustomerID(customerId: Int): Customer


}