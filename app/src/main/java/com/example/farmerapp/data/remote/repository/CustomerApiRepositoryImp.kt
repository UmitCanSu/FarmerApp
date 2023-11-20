package com.example.farmerapp.data.remote.repository

import com.example.farmerapp.data.remote.api.CustomerApi
import com.example.farmerapp.data.remote.dto.CustomerApiDto
import com.example.farmerapp.data.remote.dto.CustomerListApiDto
import com.example.farmerapp.domain.repository.remote.CustomerApiRepository
import javax.inject.Inject

class CustomerApiRepositoryImp
@Inject constructor(
    private val customerApi: CustomerApi
) : CustomerApiRepository {
    override suspend fun addCustomer(customerJson: String): CustomerApiDto {
        return customerApi.addCustomer(customerJson)
    }

    override suspend fun getAllCustomer(): List<CustomerApiDto> {
        return customerApi.getAllCustomer()
    }

    override suspend fun getCustomerWithCustomerNoOrPhoneNumber(phoneOrCustomerId: String): CustomerApiDto {
        return customerApi.getCustomerWithCustomerNoOrPhoneNumber(phoneOrCustomerId)
    }

    override suspend fun upsetCustomer(
        customerString: String,
        savedString: String,
        companyId: String
    ): CustomerListApiDto {
        return customerApi.upsetCustomer(customerString, savedString, companyId)
    }

    override suspend fun getCustomerListByCompanyId(companyId: String): CustomerListApiDto {
        return customerApi.getCustomerList(companyId)
    }
}