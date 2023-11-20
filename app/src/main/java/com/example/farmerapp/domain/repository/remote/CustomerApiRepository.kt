package com.example.farmerapp.domain.repository.remote

import com.example.farmerapp.data.remote.dto.CustomerApiDto
import com.example.farmerapp.data.remote.dto.CustomerListApiDto

interface CustomerApiRepository {
    suspend fun addCustomer(customerJson: String): CustomerApiDto
    suspend fun getAllCustomer(): List<CustomerApiDto>
    suspend fun getCustomerWithCustomerNoOrPhoneNumber(phoneOrCustomerId: String): CustomerApiDto
    suspend fun upsetCustomer(
        customerString: String, savedString: String, companyId: String
    ): CustomerListApiDto

    suspend fun getCustomerListByCompanyId(companyId: String): CustomerListApiDto
}