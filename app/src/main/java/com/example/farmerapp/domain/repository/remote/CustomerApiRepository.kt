package com.example.farmerapp.domain.repository.remote

import com.example.farmerapp.data.remote.dto.CustomerApiDto

interface CustomerApiRepository {
    suspend fun addCustomer(customerJson:String): CustomerApiDto
    suspend fun getAllCustomer():List<CustomerApiDto>
    suspend fun getCustomerWithCustomerNoOrPhoneNumber(phoneOrCustomerId:String): CustomerApiDto
}