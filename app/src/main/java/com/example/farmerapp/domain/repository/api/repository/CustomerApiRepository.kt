package com.example.farmerapp.domain.repository.api.repository

import com.example.farmerapp.data.remote.dto.CustomerApiDto
import retrofit2.http.POST

interface CustomerApiRepository {
    suspend fun addCustomer(customerJson:String): CustomerApiDto
    suspend fun getAllCustomer():List<CustomerApiDto>
    suspend fun getCustomerWithCustomerNoOrPhoneNumber(phoneOrCustomerId:String): CustomerApiDto
}