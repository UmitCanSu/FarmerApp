package com.example.farmerapp.data.local.repository

import com.example.farmerapp.data.local.dto.CustomerDto
import com.example.farmerapp.domain.repository.room.CustomerRepository
import com.example.farmerapp.domain.repository.dao.CustomerDao
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

    override suspend fun selectCustomerWithPhoneNumber(phoneNumber: String): CustomerDto {
        return customerDao.selectCustomerWithPhoneNumber(phoneNumber)
    }

    override suspend fun getAllCustomer(): List<CustomerDto> {
        return customerDao.getAllCustomer()
    }
}