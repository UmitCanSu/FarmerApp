package com.example.farmerapp.domain.repository

import com.example.farmerapp.domain.model.Customer

interface CustomerRepository {
    suspend fun insertCustomer(customer: Customer)
    suspend fun updateCustomer(customer: Customer)
    suspend fun selectCustomerWithCustomerID(customerId: Int): Customer


}