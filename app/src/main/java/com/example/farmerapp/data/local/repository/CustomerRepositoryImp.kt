package com.example.farmerapp.data.local.repository

import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.domain.repository.dao.CustomerDao
import com.example.farmerapp.domain.repository.local.CustomerRepository
import javax.inject.Inject

class CustomerRepositoryImp
@Inject constructor(
    private val customerDao: CustomerDao
) : CustomerRepository {
    override suspend fun insertCustomer(customer: CustomerDto): Long {
        return customerDao.insertCustomer(customer)
    }

    override suspend fun updateCustomer(customer: CustomerDto): Int {
        return customerDao.updateCustomer(customer)
    }

    override suspend fun selectCustomerWithCustomerID(customerId: Int): CustomerDto {
        return customerDao.selectCustomerWithCustomerID(customerId)
    }

    override suspend fun getAllCustomer(): List<CustomerDto> {
        return customerDao.getAllCustomer()
    }

    override suspend fun getCustomerByApiId(apiId: String): CustomerDto {
        return customerDao.getCustomerByApiId(apiId)
    }
}